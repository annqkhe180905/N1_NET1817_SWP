package online.ondemandtutor.be;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import online.ondemandtutor.be.validation.User;
import online.ondemandtutor.be.validation.UserService;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testChangePasswordSuccess() {
        boolean result = userService.changePassword("a", "password123", "newpassword123");
        assertTrue(result, "Password should be changed successfully");
        User user = userService.getUser("a");
        assertEquals("newpassword123", user.getPassword(), "Password should be updated to newpassword123");
    }

    @Test
    public void testChangePasswordWrongOldPassword() {
        boolean result = userService.changePassword("a", "wrongpassword", "newpassword123");
        assertFalse(result, "Password change should fail due to wrong old password");
        User user = userService.getUser("a");
        assertEquals("password123", user.getPassword(), "Password should remain unchanged");
    }

    @Test
    public void testChangePasswordNonExistentUser() {
        boolean result = userService.changePassword("koTontai", "password123", "newpassword123");
        assertFalse(result, "Password change should fail for non-existent user");
    }

    @Test
    public void testChangePasswordSameOldPassword() {
        boolean result = userService.changePassword("a", "password123", "password123");
        assertFalse(result, "Password change should fail due to same old password");
        User user = userService.getUser("a");
        assertEquals("password123", user.getPassword(), "Password should remain unchanged");
    }
}
