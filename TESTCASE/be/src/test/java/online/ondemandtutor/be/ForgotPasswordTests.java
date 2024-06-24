package online.ondemandtutor.be;
import online.ondemandtutor.be.model.ForgotPasswordRequest;
import online.ondemandtutor.be.model.ResetPasswordRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ForgotPasswordTests {
    @Test
    public void testSetAndGetEmail() {
        ForgotPasswordRequest request = new ForgotPasswordRequest();
        String testEmail = "test@example.com";
        request.setEmail(testEmail);
        assertEquals(testEmail, request.getEmail(), "The email should be correctly set and retrieved");
    }

    @Test
    public void testValidEmailFormat() {
        ForgotPasswordRequest request = new ForgotPasswordRequest();
        String validEmail = "valid@example.com";
        request.setEmail(validEmail);
        assertTrue(isValidEmail(request.getEmail()), "The email should be in a valid format");
    }

    @Test
    public void testInvalidEmailFormat() {
        ForgotPasswordRequest request = new ForgotPasswordRequest();
        String invalidEmail = "invalid-email";
        request.setEmail(invalidEmail);
        assertFalse(isValidEmail(request.getEmail()), "The email should be in an invalid format");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

}
