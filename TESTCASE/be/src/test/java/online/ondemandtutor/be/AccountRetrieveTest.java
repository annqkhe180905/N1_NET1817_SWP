package online.ondemandtutor.be;
import online.ondemandtutor.be.entity.Account;
import online.ondemandtutor.be.exception.BadRequestException;
import online.ondemandtutor.be.model.UpdateRequest;
import online.ondemandtutor.be.repository.AuthenticationRepository;
import online.ondemandtutor.be.service.AccountService;
import online.ondemandtutor.be.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
public class AccountRetrieveTest {
    @InjectMocks
    private AccountService accountService;

    @Mock
    private AuthenticationRepository authenticationRepository;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAccount_HappyCase() {

        String email = "Billy32@gmail.com";
        Account mockAccount = new Account();
        mockAccount.setEmail(email);

        when(authenticationRepository.findAccountByEmail(email)).thenReturn(mockAccount);

        // Act
        Account returnedAccount = (Account) authenticationService.loadUserByUsername(email);

        // Assert
        assertEquals(mockAccount, returnedAccount);

    }

    @Test
    public void updateAccount_UnhappyCase() {

        String email = "test23123213213Email@gmail.com";
        Account mockAccount = new Account();
        mockAccount.setEmail(email);

        when(authenticationRepository.findAccountByEmail(email)).thenReturn(mockAccount);

        // Act
        Account returnedAccount = (Account) authenticationService.loadUserByUsername(email);

        // Assert
        assertEquals(mockAccount, returnedAccount);

    }
}
