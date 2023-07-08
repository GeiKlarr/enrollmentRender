package Enrollment.Enrollment.service.Impl;

import Enrollment.Enrollment.dto.EnrolleeDto;
import Enrollment.Enrollment.models.Enrollee;
import Enrollment.Enrollment.models.UserEntity;
import Enrollment.Enrollment.repository.EnrolleeRepository;
import Enrollment.Enrollment.repository.UserRepository;
import Enrollment.Enrollment.security.SecurityUtil;
import Enrollment.Enrollment.service.EnrolleeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static Enrollment.Enrollment.mappers.EnrolleeMapper.mapToEnrollee;
import static Enrollment.Enrollment.mappers.EnrolleeMapper.mapToEnrolleeDto;

@Service
public class EnrolleeServiceImpl implements EnrolleeService {
    private EnrolleeRepository enrolleeRepository;
    private UserRepository userRepository;

    public EnrolleeServiceImpl(EnrolleeRepository enrolleeRepository, UserRepository userRepository) {
        this.enrolleeRepository = enrolleeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<EnrolleeDto> findAllEnrolled() {
        List<Enrollee> enrolleeList = enrolleeRepository.findAll();
        return enrolleeList.stream().map(enrollee -> mapToEnrolleeDto(enrollee)).collect(Collectors.toList());
    }

    @Override
    public Enrollee saveEnrolleeKinder(EnrolleeDto enrolleeDto) {
        String username = SecurityUtil.getSessionUser();
        //Get EnrolledBy using findByEmail to UserEntity
        UserEntity user = userRepository.findByEmail(username);
        Enrollee enrollee = mapToEnrollee(enrolleeDto);
        enrollee.setEnrolledIn("kindergarten");
        enrollee.setStatus("Pending");
        enrollee.setEnroledBy(user);
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public EnrolleeDto findByEnrolleeId(long enrolleeId) {
        Enrollee enrolleeDto = enrolleeRepository.findById(enrolleeId).get();
        return mapToEnrolleeDto(enrolleeDto);
    }

    @Override
    public void updateEnrollee(EnrolleeDto enrolleeDto) {
        Enrollee enrollee = mapToEnrollee(enrolleeDto);
        enrolleeRepository.save(enrollee);
    }

    @Override
    public void delete(long enrolleeId) {
        enrolleeRepository.deleteById(enrolleeId);
    }

    @Override
    public Enrollee saveEnrolleePrep(EnrolleeDto enrolleeDto) {
        String username = SecurityUtil.getSessionUser();
        //Get EnrolledBy using findByEmail to UserEntity
        UserEntity user = userRepository.findByEmail(username);
        Enrollee enrollee = mapToEnrollee(enrolleeDto);
        enrollee.setEnrolledIn("tutorial");
        enrollee.setStatus("Pending");
        enrollee.setEnroledBy(user);
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public Enrollee saveEnrolleeNursery(EnrolleeDto enrolleeDto) {
        String username = SecurityUtil.getSessionUser();
        //Get EnrolledBy using findByEmail to UserEntity
        UserEntity user = userRepository.findByEmail(username);
        Enrollee enrollee = mapToEnrollee(enrolleeDto);
        enrollee.setEnrolledIn("nursery");
        enrollee.setStatus("Pending");
        enrollee.setEnroledBy(user);
        return enrolleeRepository.save(enrollee);
    }



}
