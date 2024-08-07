package online.ondemandtutor.be.service;

import online.ondemandtutor.be.entity.Account;
import online.ondemandtutor.be.enums.RoleEnum;
import online.ondemandtutor.be.exception.BadRequestException;
import online.ondemandtutor.be.model.EmailDetail;
import online.ondemandtutor.be.model.GetAccountByIdRequest;
import online.ondemandtutor.be.model.UpRoleRequest;
import online.ondemandtutor.be.model.UpdateRequest;
import online.ondemandtutor.be.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountService {
    @Autowired
    AuthenticationRepository authenticationRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;

    //them vo github branch UpRole
    public Account UpRole(UpRoleRequest upRoleRequest){

        Account account = authenticationRepository.findAccountByEmail(upRoleRequest.getEmail());
        if(account != null){
            account.setRole(RoleEnum.TUTOR);
            return authenticationRepository.save(account);
        }
        else{
            throw new BadRequestException("Account is not found!");
        }
    }

    public void SendUpRoleRegistration(UpRoleRequest upRoleRequest){
        Account account = authenticationService.getCurrentAccount();

        //copy tu ForgetPassword
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient(account.getEmail());
        emailDetail.setSubject("Upgrade Tutor role for account " + account.getEmail() + "!");
        emailDetail.setMsgBody("");
        // chờ FE gửi web chính thức
        emailDetail.setButtonValue("Upgrade to Tutor");
        emailDetail.setFullName(account.getFullname());
        // chờ FE gửi link web up role
        emailDetail.setLink("http://ondemandtutor.online/reset-password?token=" + tokenService.generateToken(account));
        Runnable r = new Runnable() {
            @Override
            public void run() {
                emailService.sendMailTemplate(emailDetail);
            }
        };
        new Thread(r).start();

    }


    public List<Account> getAllAccounts(){
        return authenticationRepository.findAccountByIsDeletedFalse();
    }

    //chinh sua: 12:45 ngay 12/06/2024
    public Account getAccountById(long id){
        Account account = authenticationRepository.findAccountById(id);
        if(account == null){
            throw new BadRequestException("Account is not found!");
        }
        return account;
    }

    public Account updateAccount(UpdateRequest updateRequest) {
        Account account = authenticationService.getCurrentAccount();
        account.setPhone(updateRequest.getPhone());
        account.setFullname(updateRequest.getFullname());

        Account newAccount = authenticationRepository.save(account);
        return newAccount;
    }

    public Account changeStatusByAdmin (long id){
        Account account = authenticationRepository.findAccountByIdAndIsDeletedFalse(id);
        if(account != null){
            account.setDeleted(true);
            return authenticationRepository.save(account);
        }
        else {
            throw new BadRequestException("Account is not found!");
        }
    }

    public Account findAccountbyPhone(String phone){
        return authenticationRepository.findAccountByPhone(phone);
    }
}
