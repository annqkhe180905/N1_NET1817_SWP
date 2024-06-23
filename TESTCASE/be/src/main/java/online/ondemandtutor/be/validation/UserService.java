package online.ondemandtutor.be.validation;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public UserService() {
        users.put("a", new User("a", "password123"));
        users.put("b", new User("b", "password456"));
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(oldPassword) && !oldPassword.equals(newPassword)) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public User getUser(String username) {
        return users.get(username);
    }
}
