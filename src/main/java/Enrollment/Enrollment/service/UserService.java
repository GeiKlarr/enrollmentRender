package Enrollment.Enrollment.service;

import Enrollment.Enrollment.dto.RegistrationDto;
import Enrollment.Enrollment.models.UserEntity;


public interface UserService {
    void saveUser(RegistrationDto registrationDto); // Take a registration
    UserEntity findByEmail(String email);  //
    UserEntity findByUsername(String username);
}
