package online.ondemandtutor.be.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.ondemandtutor.be.entity.Account;
import online.ondemandtutor.be.model.*;
import online.ondemandtutor.be.repository.AuthenticationRepository;
import online.ondemandtutor.be.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@SecurityRequirement(name = "api")
public class AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest){
        Account account = authenticationService.register(registerRequest);
        return ResponseEntity.ok(account);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        AccountResponse accResponse = authenticationService.login(loginRequest);
        return ResponseEntity.ok(accResponse);
    }

    @PostMapping("/login-google")
    public ResponseEntity<AccountResponse> loginGg(@RequestBody LoginGoogleRequest loginGoogleRequest) {
        return ResponseEntity.ok(authenticationService.loginGoogle(loginGoogleRequest));
    }

    @PostMapping("/forget-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        authenticationService.ForgotPassword(forgotPasswordRequest);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        authenticationService.ResetPassword(resetPasswordRequest);
    }

//    @GetMapping("admin")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<?> test(){
//        Account account = authenticationService.getCurrentAccount();
//        Account account1 = authenticationRepository.findAccountByEmail(account.getEmail());
//        System.out.println(account.getUsername());
//       return ResponseEntity.ok(account1);
//    }



}
