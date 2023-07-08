package Enrollment.Enrollment.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    private String email;
    private String username;
    private String password;
}