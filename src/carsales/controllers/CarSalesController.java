package carsales.controllers;

import carsales.DatabaseConnection;
import carsales.models.AdModes;
import carsales.models.Car;
import carsales.models.Shop;
import carsales.models.User;
import carsales.views.CarSalesView;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarSalesController {
    DatabaseConnection databaseConnection;
    CarSalesView carSalesView;
    Shop shop;
    User currentUser;
    File file;

    public CarSalesController(DatabaseConnection databaseConnection, CarSalesView carSalesView, Shop shop) {
        this.databaseConnection = databaseConnection;
        this.carSalesView = carSalesView;
        this.shop = shop;
        currentUser = null;

        this.carSalesView.addShowLogInPanelListener(new LogInMenuBtnListener());
        this.carSalesView.addShowSignUpPanelListener(new SignUpMenuBtnListener());
        this.carSalesView.addCustomerLogInSubmitBtnListener(new CustomerLogInSubmitBtnListener());
        this.carSalesView.addShowCarsNewestAddedBtnListener(new ShowCarsListener("cfs.id DESC"));
        this.carSalesView.addShowCarsOldestAddedBtnListener(new ShowCarsListener("cfs.id ASC"));
        this.carSalesView.addShowCarsCheapestBtnListener(new ShowCarsListener("cfs.price ASC"));
        this.carSalesView.addShowCarsMostExpensiveBtnListener(new ShowCarsListener("cfs.price DESC"));
        this.carSalesView.addShowCarsLowMileageBtnListener(new ShowCarsListener("cfs.mileage ASC"));
        this.carSalesView.addShowCarsHighMileageBtnListener(new ShowCarsListener("cfs.mileage DESC"));
        this.carSalesView.addShowCarsNewestBtnListener(new ShowCarsListener("cfs.manufacturing_year DESC"));
        this.carSalesView.addShowCarsOldestBtnListener(new ShowCarsListener("cfs.manufacturing_year ASC"));
        this.carSalesView.addCustomerSignupSubmitListener(new CustomerSignUpSubmitListener());
        this.carSalesView.addSellerLogInSubmitBtnListener(new SellerLogInSubmitBtnListener());
        this.carSalesView.addSellerSignupSubmitListener(new SellerSignUpSubmitListener());
        this.carSalesView.addLogOutMenuBtnListener(new LogOutMenuBtnListener());
        this.carSalesView.addAddCarMenuBtnListener(new AddCarMenuBtnListener());
        this.carSalesView.addAddCarButtonListener(new AddCarButtonListener());
        this.carSalesView.addAddPictureBtnListener(new AddPictureBtnListener());
        this.carSalesView.addShowMyCarsBtnListener(new ShowMyCarsBtnListener());
        this.carSalesView.addEditMyCarsBtnListener(new EditMyCarsBtnListener());

        showCars("cfs.id DESC", AdModes.CAR_SEE_MODE);
        carSalesView.changeUserAccountMenu(currentUser);
        populateAddCarOptions();
    }

    public void showCars(String orderBy, AdModes mode) {
        try {
            shop.getCars().clear();
            for (Component component : carSalesView.getShowCarsPanel().getComponents()) {
                carSalesView.getShowCarsPanel().remove(component);
            }

            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = null;

            if (mode.equals(AdModes.CAR_SEE_MODE))
            {
                String sql = "SELECT cfs.id, cm.model_name, c.manufacturer_name, vc.description, cfs.price, cfs.mileage, cfs.manufacturing_year " +
                        "FROM cars_for_sale cfs " +
                        "JOIN car_models cm on cm.model_id = cfs.model_id " +
                        "JOIN car_manufacturers c on c.id = cm.manufacturer_id " +
                        "JOIN vehicle_category vc on vc.vehicle_category_id = cfs.vehicle_category_id " +
                        "WHERE cfs.id NOT IN (SELECT cs.cars_for_sale_id FROM cars_sold cs) ORDER BY " + orderBy;

                preparedStatement = connection.prepareStatement(sql);
            }
            else if (mode.equals(AdModes.CAR_MINE_MODE)) {
                if (currentUser.isSeller())
                {
                    String sql = "SELECT cfs.id, cm.model_name, c.manufacturer_name, vc.description, cfs.price, cfs.mileage, cfs.manufacturing_year " +
                            "FROM cars_for_sale cfs " +
                            "JOIN car_models cm on cm.model_id = cfs.model_id " +
                            "JOIN car_manufacturers c on c.id = cm.manufacturer_id " +
                            "JOIN vehicle_category vc on vc.vehicle_category_id = cfs.vehicle_category_id " +
                            "JOIN cars_sold_by_sellers csbs on csbs.cars_for_sale_id = cfs.id " +
                            "WHERE csbs.sellers_id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, currentUser.getId());
                }
                else {
                    String sql = "SELECT cfs.id, cm.model_name, c.manufacturer_name, vc.description, cfs.price, cfs.mileage, cfs.manufacturing_year " +
                            "FROM cars_for_sale cfs " +
                            "JOIN car_models cm on cm.model_id = cfs.model_id " +
                            "JOIN car_manufacturers c on c.id = cm.manufacturer_id " +
                            "JOIN vehicle_category vc on vc.vehicle_category_id = cfs.vehicle_category_id " +
                            "JOIN cars_sold cs on cs.cars_for_sale_id = cfs.id " +
                            "WHERE cs.customer_id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, currentUser.getId());
                }
            }
            else if (mode.equals(AdModes.CAR_EDIT_MODE)) {
                String sql = "SELECT cfs.id, cm.model_name, c.manufacturer_name, vc.description, cfs.price, cfs.mileage, cfs.manufacturing_year " +
                        "FROM cars_for_sale cfs " +
                        "JOIN car_models cm on cm.model_id = cfs.model_id " +
                        "JOIN car_manufacturers c on c.id = cm.manufacturer_id " +
                        "JOIN vehicle_category vc on vc.vehicle_category_id = cfs.vehicle_category_id " +
                        "JOIN cars_sold_by_sellers csbs on csbs.cars_for_sale_id = cfs.id " +
                        "WHERE csbs.sellers_id = ? AND cfs.id NOT IN (SELECT cs.cars_for_sale_id FROM cars_sold cs)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, currentUser.getId());
            }

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Car newCar = new Car(result.getInt(1), result.getString(3) + " " + result.getString(2), result.getString(4), result.getDouble(5), result.getInt(6), result.getInt(7));
                try {
                    String sql2 = "SELECT cf.description FROM car_features cf " +
                            "JOIN features_on_cars_for_sale focfs on cf.id = focfs.car_features_id " +
                            "JOIN cars_for_sale cfs on cfs.id = focfs.car_for_sale_id WHERE cfs.id = ?";

                    PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                    preparedStatement2.setInt(1, newCar.getId());
                    ResultSet resultSet2 = preparedStatement2.executeQuery();

                    while (resultSet2.next()) {
                        newCar.getFeatures().add(resultSet2.getString(1));
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                shop.getCars().add(newCar);
                carSalesView.addCarPanel(newCar, new ShowCarButtonListener(newCar, mode));
            }

            int size = shop.getCars().size();
            int dimension = (int)Math.ceil(size / 5.0) * 297 + 17;
            carSalesView.getShowCarsPanel().setPreferredSize(new Dimension(1225, dimension));

            connection.close();

            carSalesView.getShowCarsPanel().revalidate();
            carSalesView.getShowCarsPanel().repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCar(Car car) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("src/carsales/images/" + car.getId() + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Container container = carSalesView.getContentPane();
        carSalesView.getCardLayout().show(container, "showCarPanel");
        carSalesView.getCarForSalePic().setIcon(new ImageIcon(bufferedImage.getScaledInstance(603, 603 * bufferedImage.getHeight() / bufferedImage.getWidth(), Image.SCALE_SMOOTH)));
        carSalesView.setCarForSaleNameLabel(car.getCarName());
        carSalesView.setCarForSaleDetails("• Mileage: " + car.getMileage() + " • Year: " + car.getYear() + " •");

        String features = "";
        for (String feature : car.getFeatures()) {
            features += "•" + feature + "\n";
        }
        carSalesView.setCarForSaleFeaturesTextArea(features);

        carSalesView.getCarForSaleBtn().setText("BUY - " + car.getPrice() + " €");
        carSalesView.getCarForSaleBtn().setEnabled(false);

        if (currentUser != null && !currentUser.isSeller()) {
            carSalesView.getCarForSaleBtn().setEnabled(true);
            carSalesView.addCarForSaleBtnListener(new BuyCarBtnListener(car.getId()));
        }
    }

    public void populateAddCarOptions() {
        try {
            try {
                Connection connection = databaseConnection.getConnection();
                String sql = "SELECT cmo.model_name, cma.manufacturer_name FROM car_models cmo JOIN car_manufacturers cma ON cmo.manufacturer_id = cma.id";

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    carSalesView.getModelDropdown().addItem(resultSet.getString(2) + " " + resultSet.getString(1));
                    carSalesView.getModelEditDropdown().addItem(resultSet.getString(2) + " " + resultSet.getString(1));
                }
            } catch (Exception ex) {
                throw ex;
            }

            try {
                Connection connection = databaseConnection.getConnection();
                String sql = "SELECT description FROM vehicle_category";

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    carSalesView.getCategoryDropdown().addItem(resultSet.getString(1));
                    carSalesView.getCategoryEditDropdown().addItem(resultSet.getString(1));
                }
            } catch (Exception ex) {
                throw ex;
            }


            try {
                Connection connection = databaseConnection.getConnection();
                String sql = "SELECT description FROM car_features";

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                List<String> features = new ArrayList<>();

                while (resultSet.next()) {
                    features.add(resultSet.getString(1));
                }

                carSalesView.getFeaturesList().setModel(new AbstractListModel() {
                    String[] values = features.toArray(new String[0]);

                    public int getSize() {
                        return values.length;
                    }
                    public Object getElementAt(int index) {
                        return values[index];
                    }
                });

                carSalesView.getFeaturesEditList().setModel(new AbstractListModel() {
                    String[] values = features.toArray(new String[0]);

                    public int getSize() {
                        return values.length;
                    }
                    public Object getElementAt(int index) {
                        return values[index];
                    }
                });
            } catch (Exception ex) {
                throw ex;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    class SignUpMenuBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Container container = carSalesView.getContentPane();
            carSalesView.getCardLayout().show(container, "signUpPanel");
        }
    }

    class LogInMenuBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Container container = carSalesView.getContentPane();
            carSalesView.getCardLayout().show(container, "logInPanel");
        }
    }

    class ShowCarsListener implements ActionListener {

        private String orderBy;

        public ShowCarsListener(String orderBy) {
            this.orderBy = orderBy;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Container container = carSalesView.getContentPane();
            carSalesView.getCardLayout().show(container, "showAllCarsPanel");
            showCars(orderBy, AdModes.CAR_SEE_MODE);
        }
    }

    class CustomerLogInSubmitBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String error = "";
                String email = carSalesView.getCustomerLogInEmailField();
                if (email.equals("")) {
                    error += "Email field is empty\n";
                }

                String password = carSalesView.getCustomerLogInPasswordField();
                if (password.equals("")) {
                    error += "Password field is empty\n";
                }

                if (error.equals("")) {
                    Connection connection = databaseConnection.getConnection();
                    String sql = "SELECT * FROM customers WHERE email_address=? AND password=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, carSalesView.getCustomerLogInEmailField());
                    preparedStatement.setString(2, carSalesView.getCustomerLogInPasswordField());
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {
                            currentUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), false);
                        }

                        connection.close();

                        carSalesView.changeUserAccountMenu(currentUser);
                        carSalesView.showMessage("Log in successful");
                        carSalesView.setCustomerLogInEmailField("");
                        carSalesView.setCustomerLogInPasswordField("");

                        Container container = carSalesView.getContentPane();
                        carSalesView.getCardLayout().show(container, "showAllCarsPanel");
                        showCars("cfs.id DESC", AdModes.CAR_SEE_MODE);
                    }
                    else {
                        carSalesView.showErrorMessage("Email or password are wrong");
                    }
                }
                else {
                    carSalesView.showErrorMessage(error);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class ShowCarButtonListener implements MouseListener {
        private Car car;
        private AdModes mode;

        public ShowCarButtonListener(Car car, AdModes mode) {
            this.car = car;
            this.mode = mode;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                if (mode.equals(AdModes.CAR_SEE_MODE) || mode.equals(AdModes.CAR_MINE_MODE))
                {
                    showCar(car);
                    if (mode.equals(AdModes.CAR_SEE_MODE) && currentUser != null && !currentUser.isSeller()) {
                        carSalesView.getCarForSaleBtn().setEnabled(true);
                    }
                    else {
                        carSalesView.getCarForSaleBtn().setEnabled(false);
                    }
                }
                else if (mode.equals(AdModes.CAR_EDIT_MODE))
                {
                    Container container = carSalesView.getContentPane();
                    carSalesView.getCardLayout().show(container, "editCarPanel");
                    carSalesView.addDeleteCarBtnListener(new DeleteCarBtnListener(car.getId()));
                    carSalesView.addSaveCarBtnListener(new SaveCarBtnListener(car));

                    carSalesView.setYearEditField(car.getYear());
                    carSalesView.setPriceEditField(car.getPrice());
                    carSalesView.setMileageEditField(car.getMileage());

                    carSalesView.getCategoryEditDropdown().setSelectedItem(car.getVehicleCategory());
                    carSalesView.getModelEditDropdown().setSelectedItem(car.getCarName());

                    List<Integer> features = new ArrayList<>();

                    for (int i = 0; i < carSalesView.getFeaturesEditList().getModel().getSize(); i++) {
                        if (car.getFeatures().contains(carSalesView.getFeaturesEditList().getModel().getElementAt(i))) {
                            features.add(i);
                        }
                    }

                    int[] indices = features.stream().mapToInt(i->i).toArray();
                    carSalesView.getFeaturesEditList().setSelectedIndices(indices);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class CustomerSignUpSubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = databaseConnection.getConnection();
                String sql = "SELECT MAX(c.id) FROM customers c";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                int maxId = 0;

                while (resultSet.next()) {
                    maxId = resultSet.getInt(1);
                }

                int id = maxId + 1;
                String error = "";
                String name = carSalesView.getCustomerSignUpNameField();
                if (name.equals("")) {
                    error += "Name field is empty\n";
                }
                String email = carSalesView.getCustomerSignUpEmailField();
                if (email.equals("")) {
                    error += "Email field is empty\n";
                }
                String phone = carSalesView.getCustomerSignUpPhoneField();
                if (phone.equals("")) {
                    error += "Phone field is empty\n";
                }
                String password = carSalesView.getCustomerSignUpPasswordField();
                if (password.equals("")) {
                    error += "Password field is empty\n";
                }
                boolean isSeller = false;

                if (error.equals("")) {
                    String sql2 = "INSERT INTO customers (id, name, email_address, phone_number, password) VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql2);

                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, phone);
                    preparedStatement.setString(5, password);

                    preparedStatement.executeUpdate();

                    connection.close();

                    carSalesView.showMessage("Sign up successfully");

                    carSalesView.setCustomerSignUpEmailField("");
                    carSalesView.setCustomerSignUpNameField("");
                    carSalesView.setCustomerSignUpPasswordField("");
                    carSalesView.setCustomerSignUpPhoneField("");

                    currentUser = new User(id, name, email, phone, password, false);

                    carSalesView.changeUserAccountMenu(currentUser);

                    Container container = carSalesView.getContentPane();
                    carSalesView.getCardLayout().show(container, "showAllCarsPanel");
                    showCars("cfs.id DESC", AdModes.CAR_SEE_MODE);
                }
                else {
                    carSalesView.showErrorMessage(error);
                }

            } catch (SQLIntegrityConstraintViolationException exception) {
                carSalesView.showMessage("Email or phone are already used");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class SellerLogInSubmitBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String error = "";
                String email = carSalesView.getSellerLogInEmailField();
                if (email.equals("")) {
                    error += "Email field is empty\n";
                }

                String password = carSalesView.getSellerLogInPasswordField();
                if (password.equals("")) {
                    error += "Password field is empty\n";
                }

                if (error.equals("")) {
                    Connection connection = databaseConnection.getConnection();
                    String sql = "SELECT * FROM sellers WHERE email_address=? AND password=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, password);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {
                            currentUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), true);
                        }

                        connection.close();

                        carSalesView.showMessage("Log in successful");
                        carSalesView.changeUserAccountMenu(currentUser);

                        carSalesView.setSellerLogInEmailField("");
                        carSalesView.setSellerLogInPasswordField("");

                        Container container = carSalesView.getContentPane();
                        carSalesView.getCardLayout().show(container, "showAllCarsPanel");
                        showCars("cfs.id DESC", AdModes.CAR_SEE_MODE);
                    }
                    else {
                        carSalesView.showErrorMessage("Email or password are wrong");
                    }

                }
                else {
                    carSalesView.showErrorMessage(error);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class SellerSignUpSubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = databaseConnection.getConnection();
                String sql = "SELECT MAX(s.id) FROM sellers s";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                int maxId = 0;

                while (resultSet.next()) {
                    maxId = resultSet.getInt(1);
                }

                int id = maxId + 1;
                String error = "";
                String name = carSalesView.getSellerSignUpNameField();
                if (name.equals("")) {
                    error += "Name field is empty\n";
                }
                String email = carSalesView.getSellerSignUpEmailField();
                if (email.equals("")) {
                    error += "Email field is empty\n";
                }
                String phone = carSalesView.getSellerSignUpPhoneField();
                if (phone.equals("")) {
                    error += "Phone field is empty\n";
                }
                String password = carSalesView.getSellerSignUpPasswordField();
                if (password.equals("")) {
                    error += "Password field is empty\n";
                }
                boolean isSeller = true;

                if (error.equals("")) {
                    String sql2 = "INSERT INTO sellers (id, name, email_address, phone_number, password) VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql2);

                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, phone);
                    preparedStatement.setString(5, password);

                    preparedStatement.executeUpdate();

                    connection.close();

                    carSalesView.setSellerSignUpEmailField("");
                    carSalesView.setSellerSignUpNameField("");
                    carSalesView.setSellerSignUpPasswordField("");
                    carSalesView.setSellerSignUpPhoneField("");

                    carSalesView.showMessage("Sign up successfully");

                    currentUser = new User(id, name, email, phone, password, true);

                    carSalesView.changeUserAccountMenu(currentUser);

                    Container container = carSalesView.getContentPane();
                    carSalesView.getCardLayout().show(container, "showAllCarsPanel");
                    showCars("cfs.id DESC", AdModes.CAR_SEE_MODE);
                }
                else {
                    carSalesView.showErrorMessage(error);
                }

            } catch (SQLIntegrityConstraintViolationException exception) {
                carSalesView.showErrorMessage("Email or phone are already used");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class LogOutMenuBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentUser = null;
            carSalesView.changeUserAccountMenu(currentUser);
            Container container = carSalesView.getContentPane();
            carSalesView.getCardLayout().show(container, "showAllCarsPanel");
            showCars("cfs.id DESC", AdModes.CAR_SEE_MODE);
        }
    }

    class AddCarMenuBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Container container = carSalesView.getContentPane();
            carSalesView.getCardLayout().show(container, "addCarPanel");
        }
    }

    class AddCarButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = 0;
            String error = "";
            int mileage = 0;
            int year = 0;
            double price = 0;
            String model = (String) carSalesView.getModelDropdown().getSelectedItem();
            int modelIndex = 0;
            int category =  carSalesView.getCategoryDropdown().getSelectedIndex() + 1;
            int[] features = carSalesView.getFeaturesList().getSelectedIndices();

            if (features.length == 0) {
                error += "No features selected\n";
            }

            if (file == null) {
                error += "No image selected\n";
            }

            if (error.equals("")) {
                try {
                    try {
                        mileage = carSalesView.getMileageField();
                        year = carSalesView.getYearField();
                        price = carSalesView.getPriceField();
                    } catch (Exception exception) {
                        throw exception;
                    }

                    try {
                        Connection connection = databaseConnection.getConnection();
                        String sql = "SELECT MAX(cfs.id) FROM cars_for_sale cfs";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);

                        while (resultSet.next()) {
                            id = resultSet.getInt(1) + 1;
                        }
                    } catch (Exception exception) {
                        throw exception;
                    }

                    try {
                        String[] name = model.split(" ", 2);
                        model = name[1];
                        Connection connection = databaseConnection.getConnection();
                        String sql = "SELECT model_id FROM car_models WHERE model_name = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, model);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            modelIndex = resultSet.getInt(1);
                        }

                    } catch (Exception exception) {
                        throw exception;
                    }

                    try {
                        Connection connection = databaseConnection.getConnection();
                        String sql = "INSERT INTO cars_for_sale (id, model_id, vehicle_category_id, price, mileage, manufacturing_year) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, id);
                        preparedStatement.setInt(2, modelIndex);
                        preparedStatement.setInt(3, category);
                        preparedStatement.setDouble(4, price);
                        preparedStatement.setInt(5, mileage);
                        preparedStatement.setInt(6, year);
                        preparedStatement.executeUpdate();
                        connection.close();
                    } catch (Exception exception) {
                        throw exception;
                    }

                    for (int feature : features) {
                        try {
                            Connection connection = databaseConnection.getConnection();
                            String sql = "INSERT INTO features_on_cars_for_sale (car_for_sale_id, car_features_id) VALUES (?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setInt(1, id);
                            preparedStatement.setInt(2, feature + 1);
                            preparedStatement.executeUpdate();
                            connection.close();
                        } catch (Exception exception) {
                            throw exception;
                        }
                    }

                    try {
                        Connection connection = databaseConnection.getConnection();
                        String sql = "INSERT INTO cars_sold_by_sellers (sellers_id, cars_for_sale_id) VALUES (?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, currentUser.getId());
                        preparedStatement.setInt(2, id);
                        preparedStatement.executeUpdate();
                        connection.close();
                    } catch (Exception exception) {
                        throw exception;
                    }

                    String place = "src/carsales/images/" + id + ".jpg";
                    try {
                        Files.copy(file.toPath(), new File(place).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        file = null;
                    } catch (Exception ex) {
                        throw ex;
                    }

                    carSalesView.showMessage("Car added successfully");

                    Container container = carSalesView.getContentPane();
                    carSalesView.getCardLayout().show(container, "showAllCarsPanel");
                    showCars("cfs.id DESC", AdModes.CAR_MINE_MODE);
                } catch (NumberFormatException | SQLException numberFormatException) {
                    carSalesView.showErrorMessage("Input incorrect");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else {
                carSalesView.showErrorMessage(error);
            }
        }
    }

    class AddPictureBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            file = null;
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(carSalesView);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
            }
        }
    }

    class BuyCarBtnListener implements ActionListener {

        private int id;

        public BuyCarBtnListener(int id) {
            this.id = id;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = databaseConnection.getConnection();
                String sql = "INSERT INTO cars_sold (cars_for_sale_id, customer_id) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, currentUser.getId());
                preparedStatement.executeUpdate();
                connection.close();

                carSalesView.showMessage("Car bought successfully");

                Container container = carSalesView.getContentPane();
                carSalesView.getCardLayout().show(container, "showAllCarsPanel");
                showCars("cfs.id DESC", AdModes.CAR_MINE_MODE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class ShowMyCarsBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Container container = carSalesView.getContentPane();
            carSalesView.getCardLayout().show(container, "showAllCarsPanel");
            showCars("cfs.id DESC", AdModes.CAR_MINE_MODE);
        }
    }

    class EditMyCarsBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Container container = carSalesView.getContentPane();
            carSalesView.getCardLayout().show(container, "showAllCarsPanel");
            showCars("cfs.id DESC", AdModes.CAR_EDIT_MODE);
        }
    }

    class DeleteCarBtnListener implements ActionListener {
        private int id;

        public DeleteCarBtnListener(int id) {
            this.id = id;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = databaseConnection.getConnection();
                String sql = "DELETE FROM cars_for_sale WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                connection.close();
                carSalesView.showMessage("Car deleted successfully");

                Container container = carSalesView.getContentPane();
                carSalesView.getCardLayout().show(container, "showAllCarsPanel");
                showCars("cfs.id DESC", AdModes.CAR_EDIT_MODE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class SaveCarBtnListener implements ActionListener {

        private Car car;

        public SaveCarBtnListener(Car car) {
            this.car = car;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String error = "";
            int mileage = 0;
            int year = 0;
            double price = 0;
            String model = (String) carSalesView.getModelEditDropdown().getSelectedItem();
            int modelIndex = 0;
            int category =  carSalesView.getCategoryEditDropdown().getSelectedIndex() + 1;
            int[] features = carSalesView.getFeaturesEditList().getSelectedIndices();

            if (features.length == 0) {
                error += "No features selected\n";
            }

            if (error.equals("")) {
                try {
                    try {
                        mileage = carSalesView.getMileageEditField();
                        year = carSalesView.getYearEditField();
                        price = carSalesView.getPriceEditField();
                    } catch (Exception exception) {
                        throw exception;
                    }

                    try {
                        String[] name = model.split(" ", 2);
                        model = name[1];
                        Connection connection = databaseConnection.getConnection();
                        String sql = "SELECT model_id FROM car_models WHERE model_name = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, model);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            modelIndex = resultSet.getInt(1);
                        }

                    } catch (Exception exception) {
                        throw exception;
                    }

                    try {
                        Connection connection = databaseConnection.getConnection();
                        String sql = "UPDATE cars_for_sale SET model_id = ?, vehicle_category_id = ?, price = ?, mileage = ?, manufacturing_year = ? " +
                                "WHERE id = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, modelIndex);
                        preparedStatement.setInt(2, category);
                        preparedStatement.setDouble(3, price);
                        preparedStatement.setInt(4, mileage);
                        preparedStatement.setInt(5, year);
                        preparedStatement.setInt(6, car.getId());
                        preparedStatement.executeUpdate();
                        connection.close();

                        car.setCarName((String) carSalesView.getModelEditDropdown().getSelectedItem());
                        car.setVehicleCategory((String) carSalesView.getCategoryEditDropdown().getSelectedItem());
                        car.setPrice(price);
                        car.setMileage(mileage);
                        car.setYear(year);
                    } catch (Exception exception) {
                        throw exception;
                    }

                    if (file != null) {
                        try {
                            String place = "src/carsales/images/" + car.getId() + ".jpg";
                            Files.copy(file.toPath(), new File(place).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            file = null;
                        } catch (Exception ex) {
                            throw ex;
                        }
                    }

                    try {
                        Connection connection = databaseConnection.getConnection();
                        String sql = "DELETE FROM features_on_cars_for_sale WHERE car_for_sale_id = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, car.getId());
                        preparedStatement.executeUpdate();
                        connection.close();
                    } catch (Exception exception) {
                        throw exception;
                    }

                    try {
                        car.getFeatures().clear();
                        for (int feature : features) {
                            Connection connection = databaseConnection.getConnection();
                            String sql = "INSERT INTO features_on_cars_for_sale (car_for_sale_id, car_features_id) VALUES (?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setInt(1, car.getId());
                            preparedStatement.setInt(2, feature + 1);
                            preparedStatement.executeUpdate();
                            connection.close();
                            car.getFeatures().add((String) carSalesView.getFeaturesEditList().getModel().getElementAt(feature));
                        }
                    } catch (Exception exception) {
                        throw exception;
                    }

                    carSalesView.showMessage("Car information saved successfully");
                    showCar(car);
                    Container container = carSalesView.getContentPane();
                    carSalesView.getCardLayout().show(container, "showCarPanel");
                } catch (NumberFormatException | SQLException numberFormatException) {
                    carSalesView.showErrorMessage("Input incorrect");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else {
                carSalesView.showErrorMessage(error);
            }
        }
    }
}