package Enrollment.Enrollment.service;

import Enrollment.Enrollment.dto.EnrolleeDto;
import Enrollment.Enrollment.models.Enrollee;

import java.util.List;

public interface EnrolleeService {
    List<EnrolleeDto> findAllEnrolled();

    Enrollee saveEnrolleeKinder(EnrolleeDto enrolleeDto);

    EnrolleeDto findByEnrolleeId(long enrolleeId);

    void updateEnrollee(EnrolleeDto enrolleeDto);

    void delete(long enrolleeId);

    Enrollee saveEnrolleePrep(EnrolleeDto enrolleeDto);

    Enrollee saveEnrolleeNursery(EnrolleeDto enrolleeDto);


}
