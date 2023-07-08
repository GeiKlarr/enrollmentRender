package Enrollment.Enrollment.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "enrollee")
@Table
public class Enrollee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String address;
    private String contactNo;
    private String email;
    private String gender;
    private String enrolledIn;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate schedule;

    @ManyToOne
    @JoinColumn(name = "enrolled_by",nullable = false)
    private UserEntity enroledBy;


}
