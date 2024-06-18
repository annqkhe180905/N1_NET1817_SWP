package online.ondemandtutor.be;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import online.ondemandtutor.be.api.AuthenticationAPI;
import online.ondemandtutor.be.entity.Account;
import online.ondemandtutor.be.enums.RoleEnum;
import online.ondemandtutor.be.exception.AuthException;
import online.ondemandtutor.be.exception.BadRequestException;
import online.ondemandtutor.be.model.AccountResponse;
import online.ondemandtutor.be.model.LoginRequest;
import online.ondemandtutor.be.repository.AuthenticationRepository;
import online.ondemandtutor.be.service.AuthenticationService;
import online.ondemandtutor.be.service.TokenService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(MockitoJUnitRunner.class)
//public class TestLoginController {
//    private MockMvc mockMvc;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    ObjectWriter objectWriter = objectMapper.writer();
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private AuthenticationRepository authenticationRepository;
//
//    @Mock
//    private TokenService tokenService;
//
//    @InjectMocks
//    private AuthenticationService authenticationService;
//
//    private LoginRequest validLoginRequest;
//    private Account validAccount;
//
//    @InjectMocks
//    private AuthenticationAPI authenticationAPI;
//
//    LoginRequest loginReq1 = new LoginRequest("admin", "admin");
//    LoginRequest loginReq2 = new LoginRequest("string!@gmail.com", "username");
//    LoginRequest loginReq3 = new LoginRequest(" annqk@gmail.com", "string");
//    LoginRequest loginReq4 = new LoginRequest("annqkhe180905@fpt.edu.vn", "String123!");
//    LoginRequest loginReq5 = new LoginRequest("", "String123!");
//
//    @BeforeEach
//    public void setUp() {
//        validLoginRequest = new LoginRequest("valid@email.com", "validPassword");
//        validAccount = new Account("valid@email.com", "validPassword", "John Doe", "USER", false, "1234567890", null);
//    }
//
////    @Test
////    public void testLogin_Success() throws Exception {
////        Account actualResult = Account.builder()
////                .email("")
////                .password("string")
////                .build();
////
////
////    }
//
//    @Test
//    void testLoginSuccessful() {
//        when(authenticationRepository.findAccountByEmail(validLoginRequest.getEmail())).thenReturn(validAccount);
//        when(tokenService.generateToken(validAccount)).thenReturn("generatedToken");
//
//        AccountResponse response = authenticationService.login(validLoginRequest);
//
//        assertEquals(validAccount.getEmail(), response.getEmail());
//        assertEquals("generatedToken", response.getToken());
//        assertEquals(validAccount.getFullname(), response.getFullname());
//        assertEquals(validAccount.getRole(), response.getRole());
//        assertEquals(validAccount.getId(), response.getId());
//        assertEquals(validAccount.getPhone(), response.getPhone());
//        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
//    }
//
//    @Test
//    void testLoginFailedWithInvalidCredentials() {
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new BadCredentialsException("Invalid credentials"));
//
//        assertThrows(AuthException.class, () -> authenticationService.login(validLoginRequest));
//    }
//
//    @Test
//    void testLoginFailedWithDeletedAccount() {
//        Account deletedAccount = new Account("valid@email.com", "validPassword", "John Doe", "USER", true, "1234567890", null);
//        when(authenticationRepository.findAccountByEmail(validLoginRequest.getEmail())).thenReturn(deletedAccount);
//
//        assertThrows(BadRequestException.class, () -> authenticationService.login(validLoginRequest));
//    }
//
//    //
//
////    @Test
////    void testLoginSuccessful() {
////        when(authenticationRepository.findAccountByEmail(validLoginRequest.getEmail())).thenReturn(validAccount);
////        when(tokenService.generateToken(validAccount)).thenReturn("generatedToken");
////
////        AccountResponse response = authenticationService.login(validLoginRequest);
////
////        assertEquals(validAccount.getEmail(), response.getEmail());
////        assertEquals("generatedToken", response.getToken());
////        assertEquals(validAccount.getFullname(), response.getFullname());
////        assertEquals(validAccount.getRole(), response.getRole());
////        assertEquals(validAccount.getId(), response.getId());
////        assertEquals(validAccount.getPhone(), response.getPhone());
////    }
////
////    @Test
////    void testLoginFailedWithInvalidCredentials() {
////        when(authenticationRepository.findAccountByEmail(validLoginRequest.getEmail())).thenThrow(new AuthException("Email or password is not correct!"));
////
////        assertThrows(AuthException.class, () -> authenticationService.login(validLoginRequest));
////    }
////
////    @Test
////    void testLoginFailedWithDeletedAccount() {
////        Account deletedAccount = new Account("valid@email.com", "validPassword", "John Doe", "USER", true, "1234567890", null);
////        when(authenticationRepository.findAccountByEmail(validLoginRequest.getEmail())).thenReturn(deletedAccount);
////
////        assertThrows(BadRequestException.class, () -> authenticationService.login(validLoginRequest));
////    }
//
//}

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@ExtendWith(MockitoExtension.class)
public class TestLoginController {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthenticationRepository authenticationRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationAPI authenticationAPI;

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @BeforeEach
    public void setUp() {
        // Setup mock behavior or reset state before each test method
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authenticationAPI).build();

    }

    // gia? su? moi exception deu dung (ko null, ko ki tu dac biet, ko khoang trang o nhung vi tri dau tien,
    // email co 1 dau @, do dai ki tu khong vuot qua 256)

    @Test
    public void testLogin_Successful() {
        // chuan bi thong tin de login
        LoginRequest loginRequest = new LoginRequest("tet@yahoo.com", "password");
        // doi tuong Account dc gia lap de? mock
        Account mockAccount = new Account("tet@yahoo.com", "password", "Nguyen Quang Khanh An", "1234567890", RoleEnum.STUDENT, false);

        //cau hinh hanh vi cua cac doi tuong mock
        //khi goi phuong thuc findAccountByEmail cua authenticationRepository voi tham so truyen vao
        // la loginRequest.getEmail()  ==>  tra ve mockAccount
        when(authenticationRepository.findAccountByEmail(loginRequest.getEmail())).thenReturn(mockAccount);
        //khi goi phuong thuc generateToken cua tokenService voi tham so truyen vao
        // la mockAccount  ==>  tra ve mock_token
        when(tokenService.generateToken(mockAccount)).thenReturn("mocked_token");

        // response la ket qua tra ve cua phuong thuc LOGIN
        AccountResponse response = authenticationService.login(loginRequest);

        //kiem tra response co null k
        //kiem tra cac thuoc tinh cua response phai tuong duong voi cac gia tri dc thiet lap trong mockAccount
        //tham so truyen vao ben trai la EXPECTED RESULT, ben phai la ACTUAL RESULT
        assertNotNull(response);
        assertEquals(mockAccount.getEmail(), response.getEmail());
        assertEquals("mocked_token", response.getToken());
        assertEquals(mockAccount.getFullname(), response.getFullname());
        assertEquals(mockAccount.getRole(), response.getRole());
        assertEquals(mockAccount.getId(), response.getId());
        assertEquals(mockAccount.getPhone(), response.getPhone());

        //kiem tra phuong thuc authenticate cua authenticationManager co dc goi dung voi tham so tuong ung k
        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));
        //kiem tra phuong thuc findAccountByEmail cua authenticationRepository co dc goi dung voi email tuong ung k
        verify(authenticationRepository).findAccountByEmail(loginRequest.getEmail());
        //kiem tra phuong thuc generateToken cua tokenService co dc goi dung voi mockAccount tuong ung k
        verify(tokenService).generateToken(mockAccount);
    }
    // ket qua output 

    @Test
    public void testLogin_AccountDeleted() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("deleted@yahoo.com", "password");
        Account deletedAccount = new Account();
        deletedAccount.setDeleted(true);

        when(authenticationRepository.findAccountByEmail(loginRequest.getEmail())).thenReturn(deletedAccount);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            authenticationService.login(loginRequest);
        });
        assertEquals("Please try another account!", exception.getMessage());

        verify(authenticationRepository).findAccountByEmail(loginRequest.getEmail());
        verifyNoInteractions(tokenService);
    }

    @Test
    public void testLogin_InvalidCredentials() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("nonexistent@example.com", "invalid_password");

        when(authenticationRepository.findAccountByEmail(loginRequest.getEmail())).thenReturn(null);

        // Act & Assert
        AuthException exception = assertThrows(AuthException.class, () -> {
            authenticationService.login(loginRequest);
        });
        assertEquals("Email or password is not correct!", exception.getMessage());

        verify(authenticationRepository).findAccountByEmail(loginRequest.getEmail());
        verifyNoInteractions(tokenService);
    }
}
