package online.ondemandtutor.be;
import online.ondemandtutor.be.entity.Account;
import online.ondemandtutor.be.entity.Subject;
import online.ondemandtutor.be.exception.BadRequestException;
import online.ondemandtutor.be.model.SubjectRequest;
import online.ondemandtutor.be.model.UpdateRequest;
import online.ondemandtutor.be.repository.AuthenticationRepository;
import online.ondemandtutor.be.repository.SubjectRepository;
import online.ondemandtutor.be.service.AccountService;
import online.ondemandtutor.be.service.AuthenticationService;
import online.ondemandtutor.be.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



public class ServiceCreationTest {
    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void CreateTest_HappyCase(){
        SubjectRequest subjectRequest = new SubjectRequest();
        subjectRequest.setName("Math");
        subjectRequest.setCategoryId(1);
        subjectRequest.setScheduleId(101);

        Subject Asubject = new Subject();

        //How to create Servoce ?!

    }
}
