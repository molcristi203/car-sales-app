package carsales.models;

public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private boolean isSeller;

    public User(int id, String name, String email, String phone, String password, boolean isSeller) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.isSeller = isSeller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }

    @Override
    public String toString() {
        return "id: " + id + "\nname: " + name + "\nemail: " + email + "\nphone: " + phone + "\npassword: " + password + "\nis seller: " + isSeller;
    }
}
