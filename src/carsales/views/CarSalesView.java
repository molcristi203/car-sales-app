package carsales.views;

import carsales.models.Car;
import carsales.models.User;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CarSalesView extends JFrame {

    private JPanel contentPane;
    private JTextField customerSignUpNameField;
    private JTextField customerSignUpEmailField;
    private JTextField customerSignUpPhoneField;
    private JPasswordField customerSignUpPasswordField;
    private JTextField sellerSignUpNameField;
    private JTextField sellerSignUpEmailField;
    private JTextField sellerSignUpPhoneField;
    private JPasswordField sellerSignUpPasswordField;
    private JTextField customerLogInEmailField;
    private JPasswordField customerLogInPasswordField;
    private JTextField sellerLogInEmailField;
    private JPasswordField sellerLogInPasswordField;
    private JMenuItem logInMenuBtn;
    private JMenuItem signUpMenuBtn;
    private CardLayout cardLayout;
    private JButton customerLogInSubmit;
    private JPanel showCarsPanel;
    private JLabel carForSalePic;
    private JLabel carForSaleNameLabel;
    private JTextArea carForSaleDetails;
    private JTextArea carForSaleFeaturesTextArea;
    private JButton carForSaleBtn;
    private JButton customerSignUpSubmit;
    private JButton sellerLogInSubmit;
    private JButton sellerSignUpSubmit;
    private JMenu userAccountMenuBtn;
    private JMenuItem nameMenuBtn;
    private JMenuItem logOutMenuBtn;
    private JMenuItem showCarsOldest;
    private JMenuItem showCarsNewest;
    private JMenuItem showCarsLowMileage;
    private JMenuItem showCarsHighMileage;
    private JMenuItem showCarsMostExpensive;
    private JMenuItem showCarsCheapest;
    private JMenuItem showCarsOldestAdded;
    private JMenuItem showCarsNewestAdded;
    private JTextField mileageField;
    private JTextField priceField;
    private JTextField yearField;
    private JMenuItem addCarMenuBtn;
    private JPanel showCarPanel;
    private JComboBox categoryDropdown;
    private JComboBox modelDropdown;
    private JList featuresList;
    private JButton addCarButton;
    private JButton addPictureBtn;
    private JMenuItem editMyCarsMenuBtn;
    private JMenuItem showMyCarsMenuBtn;
    private JTextField mileageEditField;
    private JTextField priceEditField;
    private JTextField yearEditField;
    private JButton deleteCarBtn;
    private JComboBox categoryEditDropdown;
    private JComboBox modelEditDropdown;
    private JButton saveCarBtn;
    private JButton addPictureEditBtn;
    private JList featuresEditList;

    public CarSalesView() {
        this.setTitle("Car Sales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu showCarsMenuBtn = new JMenu("Show All Cars");
        showCarsMenuBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        menuBar.add(showCarsMenuBtn);

        showCarsNewestAdded = new JMenuItem("Newest Added");
        showCarsNewestAdded.setFont(new Font("Arial", Font.PLAIN, 18));
        showCarsMenuBtn.add(showCarsNewestAdded);

        showCarsOldestAdded = new JMenuItem("Oldest Added");
        showCarsOldestAdded.setFont(new Font("Arial", Font.PLAIN, 18));
        showCarsMenuBtn.add(showCarsOldestAdded);

        showCarsCheapest = new JMenuItem("Cheapest");
        showCarsCheapest.setFont(new Font("Arial", Font.PLAIN, 18));
        showCarsMenuBtn.add(showCarsCheapest);

        showCarsMostExpensive = new JMenuItem("Most Expensive");
        showCarsMostExpensive.setFont(new Font("Arial ", Font.PLAIN, 18));
        showCarsMenuBtn.add(showCarsMostExpensive);

        showCarsHighMileage = new JMenuItem("High Mileage");
        showCarsHighMileage.setFont(new Font("Arial", Font.PLAIN, 18));
        showCarsMenuBtn.add(showCarsHighMileage);

        showCarsLowMileage = new JMenuItem("Low Mileage");
        showCarsLowMileage.setFont(new Font("Arial", Font.PLAIN, 18));
        showCarsMenuBtn.add(showCarsLowMileage);

        showCarsNewest = new JMenuItem("Newest");
        showCarsNewest.setFont(new Font("Arial", Font.PLAIN, 18));
        showCarsMenuBtn.add(showCarsNewest);

        showCarsOldest = new JMenuItem("Oldest");
        showCarsOldest.setFont(new Font("Arial", Font.PLAIN, 18));
        showCarsMenuBtn.add(showCarsOldest);

        JMenu carsMenuBtn = new JMenu("My Cars");
        carsMenuBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        menuBar.add(carsMenuBtn);

        addCarMenuBtn = new JMenuItem("Add Car");
        addCarMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        carsMenuBtn.add(addCarMenuBtn);

        showMyCarsMenuBtn = new JMenuItem("Show My Cars");
        showMyCarsMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        carsMenuBtn.add(showMyCarsMenuBtn);

        editMyCarsMenuBtn = new JMenuItem("Edit My Cars");
        editMyCarsMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        carsMenuBtn.add(editMyCarsMenuBtn);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        userAccountMenuBtn = new JMenu("User Account");
        userAccountMenuBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        menuBar.add(userAccountMenuBtn);

        logInMenuBtn = new JMenuItem("Log In");
        logInMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));

        signUpMenuBtn = new JMenuItem("Sign Up");
        signUpMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));

        logOutMenuBtn = new JMenuItem("Log out");
        logOutMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));

        nameMenuBtn = new JMenuItem("");
        nameMenuBtn.setFont(new Font("Arial", Font.PLAIN, 18));

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        cardLayout = new CardLayout(0, 0);

        setContentPane(contentPane);
        contentPane.setLayout(cardLayout);

        showCarsPanel = new JPanel();
        showCarsPanel.setBackground(new Color(29, 53, 87));
        showCarsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 17));
        //showCarsPanel.setPreferredSize(new Dimension(1225, 612));

        JScrollPane showCarsScrollPane = new JScrollPane(showCarsPanel);
        showCarsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        showCarsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        showCarsScrollPane.setPreferredSize(new Dimension(1225, 612));
        contentPane.add(showCarsScrollPane, "showAllCarsPanel");

        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(new Color(241, 250, 238));
        contentPane.add(signUpPanel, "signUpPanel");
        signUpPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(69, 123, 157));
        panel.setBounds(0, 0, 613, 613);
        signUpPanel.add(panel);

        JLabel lblNewLabel = new JLabel("Customer Sign Up");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        lblNewLabel.setBounds(142, 33, 309, 74);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("Name:");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2.setBackground(Color.WHITE);
        lblNewLabel_2.setBounds(59, 204, 151, 38);
        panel.add(lblNewLabel_2);

        customerSignUpNameField = new JTextField();
        customerSignUpNameField.setFont(new Font("Arial", Font.PLAIN, 24));
        customerSignUpNameField.setColumns(10);
        customerSignUpNameField.setBounds(220, 205, 251, 38);
        panel.add(customerSignUpNameField);

        JLabel lblNewLabel_2_1 = new JLabel("E-Mail:");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1.setBackground(Color.WHITE);
        lblNewLabel_2_1.setBounds(59, 267, 151, 38);
        panel.add(lblNewLabel_2_1);

        customerSignUpEmailField = new JTextField();
        customerSignUpEmailField.setFont(new Font("Arial", Font.PLAIN, 24));
        customerSignUpEmailField.setColumns(10);
        customerSignUpEmailField.setBounds(220, 268, 251, 38);
        panel.add(customerSignUpEmailField);

        JLabel lblNewLabel_2_2 = new JLabel("Phone:");
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_2.setForeground(Color.WHITE);
        lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_2.setBackground(Color.WHITE);
        lblNewLabel_2_2.setBounds(59, 327, 151, 38);
        panel.add(lblNewLabel_2_2);

        customerSignUpPhoneField = new JTextField();
        customerSignUpPhoneField.setFont(new Font("Arial", Font.PLAIN, 24));
        customerSignUpPhoneField.setColumns(10);
        customerSignUpPhoneField.setBounds(220, 328, 251, 38);
        panel.add(customerSignUpPhoneField);

        JLabel lblNewLabel_2_3 = new JLabel("Password:");
        lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3.setForeground(Color.WHITE);
        lblNewLabel_2_3.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_3.setBackground(Color.WHITE);
        lblNewLabel_2_3.setBounds(59, 389, 151, 38);
        panel.add(lblNewLabel_2_3);

        customerSignUpPasswordField = new JPasswordField();
        customerSignUpPasswordField.setFont(new Font("Arial", Font.PLAIN, 24));
        customerSignUpPasswordField.setBounds(220, 389, 251, 38);
        panel.add(customerSignUpPasswordField);

        customerSignUpSubmit = new JButton("Sign Up");
        customerSignUpSubmit.setFont(new Font("Arial", Font.PLAIN, 24));
        customerSignUpSubmit.setBackground(Color.WHITE);
        customerSignUpSubmit.setBounds(213, 480, 167, 38);
        panel.add(customerSignUpSubmit);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(29, 53, 87));
        panel_1.setBounds(613, 0, 613, 613);
        signUpPanel.add(panel_1);

        JLabel lblNewLabel_1 = new JLabel("Seller Sign Up");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 36));
        lblNewLabel_1.setBounds(151, 48, 290, 43);
        panel_1.add(lblNewLabel_1);

        JLabel lblNewLabel_2_4 = new JLabel("Name:");
        lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_4.setForeground(Color.WHITE);
        lblNewLabel_2_4.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_4.setBackground(Color.WHITE);
        lblNewLabel_2_4.setBounds(79, 205, 151, 38);
        panel_1.add(lblNewLabel_2_4);

        sellerSignUpNameField = new JTextField();
        sellerSignUpNameField.setFont(new Font("Arial", Font.PLAIN, 24));
        sellerSignUpNameField.setColumns(10);
        sellerSignUpNameField.setBounds(240, 206, 251, 38);
        panel_1.add(sellerSignUpNameField);

        sellerSignUpEmailField = new JTextField();
        sellerSignUpEmailField.setFont(new Font("Arial", Font.PLAIN, 24));
        sellerSignUpEmailField.setColumns(10);
        sellerSignUpEmailField.setBounds(240, 269, 251, 38);
        panel_1.add(sellerSignUpEmailField);

        JLabel lblNewLabel_2_1_1 = new JLabel("E-Mail:");
        lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_1.setBackground(Color.WHITE);
        lblNewLabel_2_1_1.setBounds(79, 268, 151, 38);
        panel_1.add(lblNewLabel_2_1_1);

        JLabel lblNewLabel_2_2_1 = new JLabel("Phone:");
        lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_2_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_2_1.setBackground(Color.WHITE);
        lblNewLabel_2_2_1.setBounds(79, 328, 151, 38);
        panel_1.add(lblNewLabel_2_2_1);

        sellerSignUpPhoneField = new JTextField();
        sellerSignUpPhoneField.setFont(new Font("Arial", Font.PLAIN, 24));
        sellerSignUpPhoneField.setColumns(10);
        sellerSignUpPhoneField.setBounds(240, 329, 251, 38);
        panel_1.add(sellerSignUpPhoneField);

        sellerSignUpPasswordField = new JPasswordField();
        sellerSignUpPasswordField.setFont(new Font("Arial", Font.PLAIN, 24));
        sellerSignUpPasswordField.setBounds(240, 390, 251, 38);
        panel_1.add(sellerSignUpPasswordField);

        JLabel lblNewLabel_2_3_1 = new JLabel("Password:");
        lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3_1.setForeground(Color.WHITE);
        lblNewLabel_2_3_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_3_1.setBackground(Color.WHITE);
        lblNewLabel_2_3_1.setBounds(79, 390, 151, 38);
        panel_1.add(lblNewLabel_2_3_1);

        sellerSignUpSubmit = new JButton("Sign Up");
        sellerSignUpSubmit.setFont(new Font("Arial", Font.PLAIN, 24));
        sellerSignUpSubmit.setBackground(Color.WHITE);
        sellerSignUpSubmit.setBounds(213, 477, 167, 38);
        panel_1.add(sellerSignUpSubmit);

        JPanel logInPanel = new JPanel();
        logInPanel.setBackground(new Color(240, 240, 240));
        contentPane.add(logInPanel, "logInPanel");
        logInPanel.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBackground(new Color(69, 123, 157));
        panel_2.setBounds(0, 0, 613, 613);
        logInPanel.add(panel_2);

        JLabel lblNewLabel_3 = new JLabel("Customer Log In");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 36));
        lblNewLabel_3.setBounds(142, 33, 309, 74);
        panel_2.add(lblNewLabel_3);

        JLabel lblNewLabel_2_1_2 = new JLabel("E-Mail:");
        lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_2.setForeground(Color.WHITE);
        lblNewLabel_2_1_2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_2.setBackground(Color.WHITE);
        lblNewLabel_2_1_2.setBounds(59, 229, 151, 38);
        panel_2.add(lblNewLabel_2_1_2);

        customerLogInEmailField = new JTextField();
        customerLogInEmailField.setFont(new Font("Arial", Font.PLAIN, 24));
        customerLogInEmailField.setColumns(10);
        customerLogInEmailField.setBounds(220, 230, 251, 38);
        panel_2.add(customerLogInEmailField);

        JLabel lblNewLabel_2_3_2 = new JLabel("Password:");
        lblNewLabel_2_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3_2.setForeground(Color.WHITE);
        lblNewLabel_2_3_2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_3_2.setBackground(Color.WHITE);
        lblNewLabel_2_3_2.setBounds(59, 288, 151, 38);
        panel_2.add(lblNewLabel_2_3_2);

        customerLogInPasswordField = new JPasswordField();
        customerLogInPasswordField.setFont(new Font("Arial", Font.PLAIN, 24));
        customerLogInPasswordField.setBounds(220, 288, 251, 38);
        panel_2.add(customerLogInPasswordField);

        customerLogInSubmit = new JButton("Log In");
        customerLogInSubmit.setFont(new Font("Arial", Font.PLAIN, 24));
        customerLogInSubmit.setBackground(Color.WHITE);
        customerLogInSubmit.setBounds(213, 388, 167, 38);
        panel_2.add(customerLogInSubmit);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(29, 53, 87));
        panel_1_1.setBounds(613, 0, 613, 613);
        logInPanel.add(panel_1_1);

        JLabel lblNewLabel_1_1 = new JLabel("Seller Log In");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 36));
        lblNewLabel_1_1.setBounds(151, 48, 290, 43);
        panel_1_1.add(lblNewLabel_1_1);

        sellerLogInEmailField = new JTextField();
        sellerLogInEmailField.setFont(new Font("Arial", Font.PLAIN, 24));
        sellerLogInEmailField.setColumns(10);
        sellerLogInEmailField.setBounds(242, 235, 251, 38);
        panel_1_1.add(sellerLogInEmailField);

        JLabel lblNewLabel_2_1_1_1 = new JLabel("E-Mail:");
        lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_1_1.setBackground(Color.WHITE);
        lblNewLabel_2_1_1_1.setBounds(81, 234, 151, 38);
        panel_1_1.add(lblNewLabel_2_1_1_1);

        sellerLogInPasswordField = new JPasswordField();
        sellerLogInPasswordField.setFont(new Font("Arial", Font.PLAIN, 24));
        sellerLogInPasswordField.setBounds(242, 294, 251, 38);
        panel_1_1.add(sellerLogInPasswordField);

        JLabel lblNewLabel_2_3_1_1 = new JLabel("Password:");
        lblNewLabel_2_3_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_3_1_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_3_1_1.setBackground(Color.WHITE);
        lblNewLabel_2_3_1_1.setBounds(81, 294, 151, 38);
        panel_1_1.add(lblNewLabel_2_3_1_1);

        sellerLogInSubmit = new JButton("Log In");
        sellerLogInSubmit.setFont(new Font("Arial", Font.PLAIN, 24));
        sellerLogInSubmit.setBackground(Color.WHITE);
        sellerLogInSubmit.setBounds(213, 394, 167, 38);
        panel_1_1.add(sellerLogInSubmit);

        showCarPanel = new JPanel();
        showCarPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        showCarPanel.setBackground(new Color(255, 255, 255));
        contentPane.add(showCarPanel, "showCarPanel");
        showCarPanel.setLayout(null);

        carForSalePic = new JLabel("PIC");
        carForSalePic.setHorizontalAlignment(SwingConstants.CENTER);
        carForSalePic.setBounds(10, 10, 603, 339);
        showCarPanel.add(carForSalePic);

        carForSaleNameLabel = new JLabel("New label");
        carForSaleNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        carForSaleNameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        carForSaleNameLabel.setBounds(720, 10, 400, 29);
        showCarPanel.add(carForSaleNameLabel);

        carForSaleDetails = new JTextArea();
        carForSaleDetails.setEditable(false);
        carForSaleDetails.setFont(new Font("Arial", Font.PLAIN, 18));
        carForSaleDetails.setBounds(720, 49, 400, 40);
        showCarPanel.add(carForSaleDetails);

        carForSaleFeaturesTextArea = new JTextArea();
        carForSaleFeaturesTextArea.setEditable(false);
        carForSaleFeaturesTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        carForSaleFeaturesTextArea.setLineWrap(true);

        JScrollPane carForSaleFeaturesScrollPane = new JScrollPane(carForSaleFeaturesTextArea);
        carForSaleFeaturesScrollPane.setBounds(10, 359, 603, 244);
        showCarPanel.add(carForSaleFeaturesScrollPane);

        carForSaleBtn = new JButton("BUY - PPPPPPP");
        carForSaleBtn.setForeground(new Color(255, 255, 255));
        carForSaleBtn.setBackground(new Color(0xe63946));
        carForSaleBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        carForSaleBtn.setBounds(720, 105, 400, 48);
        carForSaleBtn.setBorderPainted(false);
        showCarPanel.add(carForSaleBtn);

        JPanel addCarPanel = new JPanel();
        addCarPanel.setLayout(null);
        addCarPanel.setBackground(new Color(241, 250, 238));
        contentPane.add(addCarPanel, "addCarPanel");

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBackground(new Color(69, 123, 157));
        panel_3.setBounds(0, 0, 1226, 613);
        addCarPanel.add(panel_3);

        JLabel lblNewLabel_4 = new JLabel("Add Car");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 36));
        lblNewLabel_4.setBounds(458, 10, 309, 53);
        panel_3.add(lblNewLabel_4);

        JLabel lblNewLabel_2_5 = new JLabel("Mileage:");
        lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_5.setForeground(Color.WHITE);
        lblNewLabel_2_5.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_5.setBackground(Color.WHITE);
        lblNewLabel_2_5.setBounds(131, 435, 151, 38);
        panel_3.add(lblNewLabel_2_5);

        mileageField = new JTextField();
        mileageField.setFont(new Font("Arial", Font.PLAIN, 24));
        mileageField.setColumns(10);
        mileageField.setBounds(292, 436, 251, 38);
        panel_3.add(mileageField);

        JLabel lblNewLabel_2_1_3 = new JLabel("Price:");
        lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_3.setForeground(Color.WHITE);
        lblNewLabel_2_1_3.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_3.setBackground(Color.WHITE);
        lblNewLabel_2_1_3.setBounds(131, 375, 151, 38);
        panel_3.add(lblNewLabel_2_1_3);

        priceField = new JTextField();
        priceField.setFont(new Font("Arial", Font.PLAIN, 24));
        priceField.setColumns(10);
        priceField.setBounds(292, 376, 251, 38);
        panel_3.add(priceField);

        JLabel lblNewLabel_2_2_2 = new JLabel("Year:");
        lblNewLabel_2_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_2_2.setForeground(Color.WHITE);
        lblNewLabel_2_2_2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_2_2.setBackground(Color.WHITE);
        lblNewLabel_2_2_2.setBounds(131, 316, 151, 38);
        panel_3.add(lblNewLabel_2_2_2);

        yearField = new JTextField();
        yearField.setFont(new Font("Arial", Font.PLAIN, 24));
        yearField.setColumns(10);
        yearField.setBounds(292, 317, 251, 38);
        panel_3.add(yearField);

        addCarButton = new JButton("Add Car");
        addCarButton.setFont(new Font("Arial", Font.PLAIN, 24));
        addCarButton.setBackground(Color.WHITE);
        addCarButton.setBounds(529, 540, 167, 38);
        panel_3.add(addCarButton);

        addPictureBtn = new JButton("Select File");
        addPictureBtn.setFont(new Font("Arial", Font.PLAIN, 24));
        addPictureBtn.setBounds(291, 258, 251, 38);
        panel_3.add(addPictureBtn);

        JLabel lblNewLabel_2_5_1 = new JLabel("Add Picture:");
        lblNewLabel_2_5_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_5_1.setForeground(Color.WHITE);
        lblNewLabel_2_5_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_5_1.setBackground(Color.WHITE);
        lblNewLabel_2_5_1.setBounds(131, 258, 151, 38);
        panel_3.add(lblNewLabel_2_5_1);

        JLabel lblNewLabel_2_1_3_1 = new JLabel("Select Model:");
        lblNewLabel_2_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_3_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_3_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_3_1.setBackground(Color.WHITE);
        lblNewLabel_2_1_3_1.setBounds(88, 135, 195, 38);
        panel_3.add(lblNewLabel_2_1_3_1);

        modelDropdown = new JComboBox();
        modelDropdown.setFont(new Font("Arial", Font.PLAIN, 24));
        modelDropdown.setBounds(292, 135, 251, 38);
        panel_3.add(modelDropdown);

        categoryDropdown = new JComboBox();
        categoryDropdown.setFont(new Font("Arial", Font.PLAIN, 24));
        categoryDropdown.setBounds(292, 196, 251, 38);
        panel_3.add(categoryDropdown);

        JLabel lblNewLabel_2_1_3_1_2 = new JLabel("Select Category:");
        lblNewLabel_2_1_3_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_3_1_2.setForeground(Color.WHITE);
        lblNewLabel_2_1_3_1_2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_3_1_2.setBackground(Color.WHITE);
        lblNewLabel_2_1_3_1_2.setBounds(88, 196, 195, 38);
        panel_3.add(lblNewLabel_2_1_3_1_2);

        JLabel lblNewLabel_2_1_3_1_2_1 = new JLabel("Select Features:");
        lblNewLabel_2_1_3_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_3_1_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_3_1_2_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_3_1_2_1.setBackground(Color.WHITE);
        lblNewLabel_2_1_3_1_2_1.setBounds(802, 87, 195, 38);
        panel_3.add(lblNewLabel_2_1_3_1_2_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(729, 135, 358, 339);
        panel_3.add(scrollPane);

        featuresList = new JList();
        scrollPane.setViewportView(featuresList);
        featuresList.setFont(new Font("Arial", Font.PLAIN, 24));

        JPanel editCarPanel = new JPanel();
        editCarPanel.setLayout(null);
        editCarPanel.setBackground(new Color(241, 250, 238));
        contentPane.add(editCarPanel, "editCarPanel");

        JPanel panel_3_1 = new JPanel();
        panel_3_1.setLayout(null);
        panel_3_1.setBackground(new Color(69, 123, 157));
        panel_3_1.setBounds(0, 0, 1226, 613);
        editCarPanel.add(panel_3_1);

        JLabel lblNewLabel_4_1 = new JLabel("Edit Car");
        lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1.setForeground(Color.WHITE);
        lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 36));
        lblNewLabel_4_1.setBounds(458, 10, 309, 53);
        panel_3_1.add(lblNewLabel_4_1);

        JLabel lblNewLabel_2_5_2 = new JLabel("Mileage:");
        lblNewLabel_2_5_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_5_2.setForeground(Color.WHITE);
        lblNewLabel_2_5_2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_5_2.setBackground(Color.WHITE);
        lblNewLabel_2_5_2.setBounds(131, 435, 151, 38);
        panel_3_1.add(lblNewLabel_2_5_2);

        mileageEditField = new JTextField();
        mileageEditField.setFont(new Font("Arial", Font.PLAIN, 24));
        mileageEditField.setColumns(10);
        mileageEditField.setBounds(292, 436, 251, 38);
        panel_3_1.add(mileageEditField);

        JLabel lblNewLabel_2_1_3_2 = new JLabel("Price:");
        lblNewLabel_2_1_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_3_2.setForeground(Color.WHITE);
        lblNewLabel_2_1_3_2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_3_2.setBackground(Color.WHITE);
        lblNewLabel_2_1_3_2.setBounds(131, 375, 151, 38);
        panel_3_1.add(lblNewLabel_2_1_3_2);

        priceEditField = new JTextField();
        priceEditField.setFont(new Font("Arial", Font.PLAIN, 24));
        priceEditField.setColumns(10);
        priceEditField.setBounds(292, 376, 251, 38);
        panel_3_1.add(priceEditField);

        JLabel lblNewLabel_2_2_2_1 = new JLabel("Year:");
        lblNewLabel_2_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_2_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_2_2_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_2_2_1.setBackground(Color.WHITE);
        lblNewLabel_2_2_2_1.setBounds(131, 316, 151, 38);
        panel_3_1.add(lblNewLabel_2_2_2_1);

        yearEditField = new JTextField();
        yearEditField.setFont(new Font("Arial", Font.PLAIN, 24));
        yearEditField.setColumns(10);
        yearEditField.setBounds(292, 317, 251, 38);
        panel_3_1.add(yearEditField);

        addPictureEditBtn = new JButton("Select File");
        addPictureEditBtn.setFont(new Font("Arial", Font.PLAIN, 24));
        addPictureEditBtn.setBounds(291, 258, 251, 38);
        panel_3_1.add(addPictureEditBtn);

        JLabel lblNewLabel_2_5_1_1 = new JLabel("Add Picture:");
        lblNewLabel_2_5_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_5_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_5_1_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_5_1_1.setBackground(Color.WHITE);
        lblNewLabel_2_5_1_1.setBounds(131, 258, 151, 38);
        panel_3_1.add(lblNewLabel_2_5_1_1);

        JLabel lblNewLabel_2_1_3_1_1 = new JLabel("Select Model:");
        lblNewLabel_2_1_3_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_3_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_3_1_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_3_1_1.setBackground(Color.WHITE);
        lblNewLabel_2_1_3_1_1.setBounds(88, 135, 195, 38);
        panel_3_1.add(lblNewLabel_2_1_3_1_1);

        modelEditDropdown = new JComboBox();
        modelEditDropdown.setFont(new Font("Arial", Font.PLAIN, 24));
        modelEditDropdown.setBounds(292, 135, 251, 38);
        panel_3_1.add(modelEditDropdown);

        categoryEditDropdown = new JComboBox();
        categoryEditDropdown.setFont(new Font("Arial", Font.PLAIN, 24));
        categoryEditDropdown.setBounds(292, 196, 251, 38);
        panel_3_1.add(categoryEditDropdown);

        JLabel lblNewLabel_2_1_3_1_2_2 = new JLabel("Select Category:");
        lblNewLabel_2_1_3_1_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_3_1_2_2.setForeground(Color.WHITE);
        lblNewLabel_2_1_3_1_2_2.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_3_1_2_2.setBackground(Color.WHITE);
        lblNewLabel_2_1_3_1_2_2.setBounds(88, 196, 195, 38);
        panel_3_1.add(lblNewLabel_2_1_3_1_2_2);

        JLabel lblNewLabel_2_1_3_1_2_1_1 = new JLabel("Select Features:");
        lblNewLabel_2_1_3_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1_3_1_2_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_3_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 24));
        lblNewLabel_2_1_3_1_2_1_1.setBackground(Color.WHITE);
        lblNewLabel_2_1_3_1_2_1_1.setBounds(802, 87, 195, 38);
        panel_3_1.add(lblNewLabel_2_1_3_1_2_1_1);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_1.setBounds(729, 135, 358, 339);
        panel_3_1.add(scrollPane_1);

        featuresEditList = new JList();
        featuresEditList.setFont(new Font("Arial", Font.PLAIN, 24));
        scrollPane_1.setViewportView(featuresEditList);

        deleteCarBtn = new JButton("Delete");
        deleteCarBtn.setFont(new Font("Arial", Font.PLAIN, 24));
        deleteCarBtn.setBackground(Color.WHITE);
        deleteCarBtn.setBounds(659, 540, 167, 38);
        panel_3_1.add(deleteCarBtn);

        saveCarBtn = new JButton("Save");
        saveCarBtn.setFont(new Font("Arial", Font.PLAIN, 24));
        saveCarBtn.setBackground(Color.WHITE);
        saveCarBtn.setBounds(421, 540, 167, 38);
        panel_3_1.add(saveCarBtn);

        this.setVisible(true);
    }

    public JMenuItem getLogInMenuBtn() {
        return logInMenuBtn;
    }

    public void setLogInMenuBtn(JMenuItem logInMenuBtn) {
        this.logInMenuBtn = logInMenuBtn;
    }

    public JMenuItem getSignUpMenuBtn() {
        return signUpMenuBtn;
    }

    public void setSignUpMenuBtn(JMenuItem signUpMenuBtn) {
        this.signUpMenuBtn = signUpMenuBtn;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public String getCustomerLogInEmailField() {
        return customerLogInEmailField.getText();
    }

    public void setCustomerLogInEmailField(String customerLogInEmailField) {
        this.customerLogInEmailField.setText(customerLogInEmailField);
    }

    public String getCustomerLogInPasswordField() {
        return String.valueOf(customerLogInPasswordField.getPassword());
    }

    public void setCustomerLogInPasswordField(String customerLogInPasswordField) {
        this.customerLogInPasswordField.setText(customerLogInPasswordField);
    }

    public JButton getCustomerLogInSubmit() {
        return customerLogInSubmit;
    }

    public void setCustomerLogInSubmit(JButton customerLogInSubmit) {
        this.customerLogInSubmit = customerLogInSubmit;
    }

    public JPanel getShowCarsPanel() {
        return showCarsPanel;
    }

    public void setShowCarsPanel(JPanel showCarsPanel) {
        this.showCarsPanel = showCarsPanel;
    }

    public JLabel getCarForSalePic() {
        return carForSalePic;
    }

    public void setCarForSalePic(JLabel carForSalePic) {
        this.carForSalePic = carForSalePic;
    }

    public String getCarForSaleNameLabel() {
        return carForSaleNameLabel.getText();
    }

    public void setCarForSaleNameLabel(String carForSaleNameLabel) {
        this.carForSaleNameLabel.setText(carForSaleNameLabel);
    }

    public String getCarForSaleDetails() {
        return carForSaleDetails.getText();
    }

    public void setCarForSaleDetails(String carForSaleDetails) {
        this.carForSaleDetails.setText(carForSaleDetails);
    }

    public String getCarForSaleFeaturesTextArea() {
        return carForSaleFeaturesTextArea.getText();
    }

    public void setCarForSaleFeaturesTextArea(String carForSaleFeaturesTextArea) {
        this.carForSaleFeaturesTextArea.setText(carForSaleFeaturesTextArea);
    }

    public JButton getCarForSaleBtn() {
        return carForSaleBtn;
    }

    public void setCarForSaleBtn(JButton carForSaleBtn) {
        this.carForSaleBtn = carForSaleBtn;
    }

    public JButton getCustomerSignUpSubmit() {
        return customerSignUpSubmit;
    }

    public void setCustomerSignUpSubmit(JButton customerSignUpSubmit) {
        this.customerSignUpSubmit = customerSignUpSubmit;
    }

    public String getCustomerSignUpNameField() {
        return customerSignUpNameField.getText();
    }

    public void setCustomerSignUpNameField(String customerSignUpNameField) {
        this.customerSignUpNameField.setText(customerSignUpNameField);
    }

    public String getCustomerSignUpEmailField() {
        return customerSignUpEmailField.getText();
    }

    public void setCustomerSignUpEmailField(String customerSignUpEmailField) {
        this.customerSignUpEmailField.setText(customerSignUpEmailField);
    }

    public String getCustomerSignUpPhoneField() {
        return customerSignUpPhoneField.getText();
    }

    public void setCustomerSignUpPhoneField(String customerSignUpPhoneField) {
        this.customerSignUpPhoneField.setText(customerSignUpPhoneField);
    }

    public String getCustomerSignUpPasswordField() {
        return String.valueOf(customerSignUpPasswordField.getPassword());
    }

    public void setCustomerSignUpPasswordField(String customerSignUpPasswordField) {
        this.customerSignUpPasswordField.setText(customerSignUpPasswordField);
    }

    public String getSellerSignUpNameField() {
        return sellerSignUpNameField.getText();
    }

    public void setSellerSignUpNameField(String sellerSignUpNameField) {
        this.sellerSignUpNameField.setText(sellerSignUpNameField);
    }

    public String getSellerSignUpEmailField() {
        return sellerSignUpEmailField.getText();
    }

    public void setSellerSignUpEmailField(String sellerSignUpEmailField) {
        this.sellerSignUpEmailField.setText(sellerSignUpEmailField);
    }

    public String getSellerSignUpPhoneField() {
        return sellerSignUpPhoneField.getText();
    }

    public void setSellerSignUpPhoneField(String sellerSignUpPhoneField) {
        this.sellerSignUpPhoneField.setText(sellerSignUpPhoneField);
    }

    public String getSellerSignUpPasswordField() {
        return String.valueOf(sellerSignUpPasswordField.getPassword());
    }

    public void setSellerSignUpPasswordField(String sellerSignUpPasswordField) {
        this.sellerSignUpPasswordField.setText(sellerSignUpPasswordField);
    }

    public String getSellerLogInEmailField() {
        return sellerLogInEmailField.getText();
    }

    public void setSellerLogInEmailField(String sellerLogInEmailField) {
        this.sellerLogInEmailField.setText(sellerLogInEmailField);
    }

    public String getSellerLogInPasswordField() {
        return String.valueOf(sellerLogInPasswordField.getPassword());
    }

    public void setSellerLogInPasswordField(String sellerLogInPasswordField) {
        this.sellerLogInPasswordField.setText(sellerLogInPasswordField);
    }

    public JComboBox getCategoryDropdown() {
        return categoryDropdown;
    }

    public void setCategoryDropdown(JComboBox categoryDropdown) {
        this.categoryDropdown = categoryDropdown;
    }

    public JComboBox getModelDropdown() {
        return modelDropdown;
    }

    public void setModelDropdown(JComboBox modelDropdown) {
        this.modelDropdown = modelDropdown;
    }

    public JList getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(JList featuresList) {
        this.featuresList = featuresList;
    }

    public int getMileageField() {
        return Integer.parseInt(mileageField.getText());
    }

    public void setMileageField(int mileageField) {
        this.mileageField.setText(String.valueOf(mileageField));
    }

    public double getPriceField() {
        return Double.parseDouble(priceField.getText());
    }

    public void setPriceField(double priceField) {
        this.priceField.setText(priceField + "");
    }

    public int getYearField() {
        return Integer.parseInt(yearField.getText());
    }

    public void setYearField(int yearField) {
        this.yearField.setText(yearField + "");
    }

    public JButton getAddCarButton() {
        return addCarButton;
    }

    public void setAddCarButton(JButton addCarButton) {
        this.addCarButton = addCarButton;
    }

    public JButton getDeleteCarBtn() {
        return deleteCarBtn;
    }

    public void setDeleteCarBtn(JButton deleteCarBtn) {
        this.deleteCarBtn = deleteCarBtn;
    }

    public JButton getSaveCarBtn() {
        return saveCarBtn;
    }

    public void setSaveCarBtn(JButton saveCarBtn) {
        this.saveCarBtn = saveCarBtn;
    }

    public int getMileageEditField() {
        return Integer.parseInt(mileageEditField.getText());
    }

    public void setMileageEditField(int mileageEditField) {
        this.mileageEditField.setText(String.valueOf(mileageEditField));
    }

    public double getPriceEditField() {
        return Double.parseDouble(priceEditField.getText());
    }

    public void setPriceEditField(double priceEditField) {
        this.priceEditField.setText(String.valueOf(priceEditField));
    }

    public int getYearEditField() {
        return Integer.parseInt(yearEditField.getText());
    }

    public void setYearEditField(int yearEditField) {
        this.yearEditField.setText(String.valueOf(yearEditField));
    }

    public JComboBox getCategoryEditDropdown() {
        return categoryEditDropdown;
    }

    public void setCategoryEditDropdown(JComboBox categoryEditDropdown) {
        this.categoryEditDropdown = categoryEditDropdown;
    }

    public JComboBox getModelEditDropdown() {
        return modelEditDropdown;
    }

    public void setModelEditDropdown(JComboBox modelEditDropdown) {
        this.modelEditDropdown = modelEditDropdown;
    }

    public JButton getAddPictureEditBtn() {
        return addPictureEditBtn;
    }

    public void setAddPictureEditBtn(JButton addPictureEditBtn) {
        this.addPictureEditBtn = addPictureEditBtn;
    }

    public JList getFeaturesEditList() {
        return featuresEditList;
    }

    public void setFeaturesEditList(JList featuresEditList) {
        this.featuresEditList = featuresEditList;
    }

    public void addShowLogInPanelListener(ActionListener actionListener) {
        logInMenuBtn.addActionListener(actionListener);
    }

    public void addShowSignUpPanelListener(ActionListener actionListener) {
        signUpMenuBtn.addActionListener(actionListener);
    }

    public void addCustomerLogInSubmitBtnListener(ActionListener actionListener) {
        customerLogInSubmit.addActionListener(actionListener);
    }

    public void addSellerLogInSubmitBtnListener(ActionListener actionListener) {
        sellerLogInSubmit.addActionListener(actionListener);
    }

    public void addShowCarsNewestAddedBtnListener(ActionListener actionListener) {
        showCarsNewestAdded.addActionListener(actionListener);
    }

    public void addShowCarsOldestAddedBtnListener(ActionListener actionListener) {
        showCarsOldestAdded.addActionListener(actionListener);
    }

    public void addShowCarsCheapestBtnListener(ActionListener actionListener) {
        showCarsCheapest.addActionListener(actionListener);
    }

    public void addShowCarsMostExpensiveBtnListener(ActionListener actionListener) {
        showCarsMostExpensive.addActionListener(actionListener);
    }

    public void addShowCarsHighMileageBtnListener(ActionListener actionListener) {
        showCarsHighMileage.addActionListener(actionListener);
    }

    public void addShowCarsLowMileageBtnListener(ActionListener actionListener) {
        showCarsLowMileage.addActionListener(actionListener);
    }

    public void addShowCarsNewestBtnListener(ActionListener actionListener) {
        showCarsNewest.addActionListener(actionListener);
    }

    public void addShowCarsOldestBtnListener(ActionListener actionListener) {
        showCarsOldest.addActionListener(actionListener);
    }

    public void addCustomerSignupSubmitListener(ActionListener actionListener) {
        customerSignUpSubmit.addActionListener(actionListener);
    }

    public void addSellerSignupSubmitListener(ActionListener actionListener) {
        sellerSignUpSubmit.addActionListener(actionListener);
    }

    public void addLogOutMenuBtnListener(ActionListener actionListener) {
        logOutMenuBtn.addActionListener(actionListener);
    }

    public void addAddCarMenuBtnListener(ActionListener actionListener) {
        addCarMenuBtn.addActionListener(actionListener);
    }

    public void addAddCarButtonListener(ActionListener actionListener) {
        addCarButton.addActionListener(actionListener);
    }

    public void addAddPictureBtnListener(ActionListener actionListener) {
        addPictureBtn.addActionListener(actionListener);
    }

    public void addCarForSaleBtnListener(ActionListener actionListener) {
        carForSaleBtn.addActionListener(actionListener);
    }

    public void addShowMyCarsBtnListener(ActionListener actionListener) {
        showMyCarsMenuBtn.addActionListener(actionListener);
    }

    public void addEditMyCarsBtnListener(ActionListener actionListener) {
        editMyCarsMenuBtn.addActionListener(actionListener);
    }

    public void addDeleteCarBtnListener(ActionListener actionListener) {
        deleteCarBtn.addActionListener(actionListener);
    }

    public void addSaveCarBtnListener(ActionListener actionListener) {
        saveCarBtn.addActionListener(actionListener);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "", JOptionPane.PLAIN_MESSAGE);
    }

    public void addCarPanel(Car car, MouseListener mouseListener) {
        JPanel carPanel = new JPanel();
        carPanel.setBackground(new Color(255, 255, 255));
        carPanel.setPreferredSize(new Dimension(221, 280));
        showCarsPanel.add(carPanel);
        GridBagLayout gbl_carPanel = new GridBagLayout();
        gbl_carPanel.columnWidths = new int[]{0, 0, 0, 0, 0, };
        gbl_carPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 15};
        gbl_carPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 4.9E-324};
        gbl_carPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        carPanel.setLayout(gbl_carPanel);

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/carsales/images/" + car.getId() + ".jpg"));
            JLabel carPictureLabel = new JLabel(new ImageIcon(bufferedImage.getScaledInstance(221, 221 * bufferedImage.getHeight() / bufferedImage.getWidth(), Image.SCALE_SMOOTH)));
            GridBagConstraints gbc_carPictureLabel = new GridBagConstraints();
            gbc_carPictureLabel.insets = new Insets(0, 0, 5, 0);
            gbc_carPictureLabel.gridx = 0;
            gbc_carPictureLabel.gridy = 0;
            gbc_carPictureLabel.gridwidth = 4;
            gbc_carPictureLabel.gridheight = 5;
            gbc_carPictureLabel.weightx = 1;
            carPanel.add(carPictureLabel, gbc_carPictureLabel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel carNameLabel = new JLabel(car.getCarName());
        carNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        carNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        GridBagConstraints gbc_carNameLabel = new GridBagConstraints();
        gbc_carNameLabel.insets = new Insets(0, 0, 5, 0);
        gbc_carNameLabel.gridx = 0;
        gbc_carNameLabel.gridy = 5;
        gbc_carNameLabel.gridwidth = 4;
        gbc_carNameLabel.weightx = 1;
        carPanel.add(carNameLabel, gbc_carNameLabel);

        JLabel carYearLabel = new JLabel(String.valueOf(car.getYear()));
        carYearLabel.setHorizontalAlignment(SwingConstants.LEFT);
        carYearLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        GridBagConstraints gbc_carYearLabel = new GridBagConstraints();
        gbc_carYearLabel.insets = new Insets(0, 0, 5, 5);
        gbc_carYearLabel.gridx = 0;
        gbc_carYearLabel.gridy = 6;
        gbc_carYearLabel.gridwidth = 2;
        gbc_carYearLabel.weightx = 1;
        carPanel.add(carYearLabel, gbc_carYearLabel);

        JLabel carMileageLabel = new JLabel(String.valueOf(car.getMileage()) + " km");
        carMileageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        carMileageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        GridBagConstraints gbc_carMileageLabel = new GridBagConstraints();
        gbc_carMileageLabel.insets = new Insets(0, 0, 5, 0);
        gbc_carMileageLabel.gridx = 2;
        gbc_carMileageLabel.gridy = 6;
        gbc_carMileageLabel.gridwidth = 2;
        gbc_carMileageLabel.weightx = 1;
        carPanel.add(carMileageLabel, gbc_carMileageLabel);

        JLabel carPriceLabel = new JLabel(String.valueOf(car.getPrice()) + "€");
        carPriceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        GridBagConstraints gbc_carPriceLabel = new GridBagConstraints();
        gbc_carPriceLabel.insets = new Insets(0, 0, 0, 5);
        gbc_carPriceLabel.gridx = 0;
        gbc_carPriceLabel.gridy = 7;
        gbc_carPriceLabel.gridwidth = 4;
        gbc_carPriceLabel.weightx = 1;
        carPanel.add(carPriceLabel, gbc_carPriceLabel);

        carPanel.addMouseListener(mouseListener);

        carPanel.revalidate();
    }

    public void changeUserAccountMenu(User user) {
        for (Component component : userAccountMenuBtn.getMenuComponents()) {
            userAccountMenuBtn.remove(component);
        }

        addCarMenuBtn.setEnabled(false);
        editMyCarsMenuBtn.setEnabled(false);
        showMyCarsMenuBtn.setEnabled(false);

        if (user != null) {
            userAccountMenuBtn.add(nameMenuBtn);
            nameMenuBtn.setText(user.getName());
            userAccountMenuBtn.add(logOutMenuBtn);

            showMyCarsMenuBtn.setEnabled(true);

            if (user.isSeller()) {
                addCarMenuBtn.setEnabled(true);
                editMyCarsMenuBtn.setEnabled(true);
            }
        } else {
            userAccountMenuBtn.add(logInMenuBtn);
            userAccountMenuBtn.add(signUpMenuBtn);
        }
    }
}
