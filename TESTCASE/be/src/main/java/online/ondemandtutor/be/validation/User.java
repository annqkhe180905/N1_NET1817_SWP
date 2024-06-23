package online.ondemandtutor.be.validation;

public class User {
    private String username;
    private String password;

    // Constructor, getters, and setters
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
