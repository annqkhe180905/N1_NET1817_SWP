package online.ondemandtutor.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Booking {
    @ManyToOne
    @JoinColumn(name = "tutorSchedule_id")
    @JsonIgnore
    TutorSchedule tutorSchedule;

    @OneToOne
    @JoinColumn(name = "review")
    @JsonIgnore
    private Review review;

    @OneToOne
    @JoinColumn(name = "account")
    @JsonIgnore
    private Complaint complaint;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String subject;
    private String description;
    private int money;
    private String location;

    //private String url;
}
