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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class updateProfileTests {
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
    public void updateAccount_HappyCase() {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setPhone("09217261");
        updateRequest.setFullname("Khai Phan Quang");

        Account existingAccount = new Account();
        existingAccount.setPhone("3928128379");
        existingAccount.setFullname("Phan Quang Khai");

        when(authenticationService.getCurrentAccount()).thenReturn(existingAccount);
        when(authenticationRepository.save(any(Account.class))).thenAnswer(i -> i.getArguments()[0]);

        Account result = accountService.updateAccount(updateRequest);

        assertEquals(updateRequest.getPhone(), result.getPhone());
        assertEquals(updateRequest.getFullname(), result.getFullname());
    }

    @Test
    public void updateAccount_UnhappyCase() {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setPhone("09217261");
        updateRequest.setFullname("Khai Phan Quang");

        when(authenticationService.getCurrentAccount()).thenReturn(null);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            accountService.updateAccount(updateRequest);
        });

        assertEquals("Account is not found!", exception.getMessage());

    }

    @Test
    public void TestDuplicatePhone_HappyCase() {
        //HappyCase --> No Phone Number duplication detected

        UpdateRequest updateRequest = new UpdateRequest();
        //Request an Update with duplicated phone number
        updateRequest.setPhone("3928128379");
        updateRequest.setFullname("Khai Phan Quang");

        //Create an account that exist in Database
        Account genericAccount = new Account();
        genericAccount.setPhone("3928128379");
        genericAccount.setFullname("Phan Quang Khai");

        //Create an account that will be updated
        Account mockAccount = new Account();
        mockAccount.setPhone("3213213213213");
        mockAccount.setFullname("Nguyen Ca No");

        when(authenticationService.getCurrentAccount()).thenReturn(mockAccount);
        when(accountService.findAccountbyPhone (updateRequest.getPhone()) ).thenReturn(genericAccount);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            accountService.updateAccount(updateRequest);
        });

        assertEquals("Phone number is already in use!", exception.getMessage());

    }

    @Test
    public void TestDuplicatePhone_UnhappyCase() {
        //unhappyCase --> Phone Number duplication detected

        UpdateRequest updateRequest = new UpdateRequest();
        //Request an Update with duplicated phone number
        updateRequest.setPhone("09392738273");
        updateRequest.setFullname("Khai Phan Quang");

        //Create an account that exist in Database
        Account genericAccount = new Account();
        genericAccount.setPhone("3928128379");
        genericAccount.setFullname("Phan Quang Khai");

        //Create an account that will be updated
        Account mockAccount = new Account();
        mockAccount.setPhone("3213213213213");
        mockAccount.setFullname("Nguyen Ca No");

        when(authenticationService.getCurrentAccount()).thenReturn(mockAccount);
        when(accountService.findAccountbyPhone (updateRequest.getPhone()) ).thenReturn(genericAccount);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            accountService.updateAccount(updateRequest);
        });

        assertEquals("Phone number is already in use!", exception.getMessage());


    }
}
