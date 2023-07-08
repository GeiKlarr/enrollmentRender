package Enrollment.Enrollment.mappers;

import Enrollment.Enrollment.dto.EnrolleeDto;
import Enrollment.Enrollment.models.Enrollee;

public class EnrolleeMapper {

    public static EnrolleeDto mapToEnrolleeDto(Enrollee enrollee){
        EnrolleeDto enrolleeDto = EnrolleeDto.builder()
                .id(enrollee.getId())
                .firstName(enrollee.getFirstName())
                .middleName(enrollee.getMiddleName())
                .lastName(enrollee.getLastName())
                .birthday(enrollee.getBirthday())
                .address(enrollee.getAddress())
                .contactNo(enrollee.getContactNo())
                .email(enrollee.getEmail())
                .gender(enrollee.getGender())
                .enrolledIn(enrollee.getEnrolledIn())
                .enrolledBy(enrollee.getEnroledBy())
                .status(enrollee.getStatus())
                .schedule(enrollee.getSchedule())
                .build();
    return enrolleeDto;
    }
    public static Enrollee mapToEnrollee(EnrolleeDto enrolleeDto){
        Enrollee enrollee = Enrollee.builder()
                .id(enrolleeDto.getId())
                .firstName(enrolleeDto.getFirstName())
                .middleName(enrolleeDto.getMiddleName())
                .lastName(enrolleeDto.getLastName())
                .birthday(enrolleeDto.getBirthday())
                .address(enrolleeDto.getAddress())
                .contactNo(enrolleeDto.getContactNo())
                .email(enrolleeDto.getEmail())
                .gender(enrolleeDto.getGender())
                .enrolledIn(enrolleeDto.getEnrolledIn())
                .enroledBy(enrolleeDto.getEnrolledBy())
                .status(enrolleeDto.getStatus())
                .schedule(enrolleeDto.getSchedule())
                .build();
        return enrollee;
    }


}
