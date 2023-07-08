package Enrollment.Enrollment.dto;


import Enrollment.Enrollment.models.UserEntity;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Data
public class EnrolleeDto {
    private Long id;
    @NotEmpty(message = "This field should not be empty")
    private String firstName;
    @NotEmpty (message = "This field should not be empty")
    private String middleName;
    @NotEmpty (message = "This field should not be empty")
    private String lastName;

    private LocalDate birthday;
    @NotEmpty (message = "This field should not be empty")
    private String address;
    @NotEmpty (message = "This field should not be empty")
    private String contactNo;
    @NotEmpty (message = "This field should not be empty")
    private String email;
    @NotEmpty (message = "This field should not be empty")
    private String gender;
    private String enrolledIn;
    private String status;
    private UserEntity enrolledBy;
    private LocalDate schedule;
}
