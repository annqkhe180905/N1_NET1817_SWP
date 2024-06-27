package online.ondemandtutor.be.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UpdateRequest {
    private String fullname;

    private String phone;
}
