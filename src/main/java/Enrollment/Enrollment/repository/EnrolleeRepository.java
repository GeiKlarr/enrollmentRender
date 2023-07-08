package Enrollment.Enrollment.repository;

import Enrollment.Enrollment.models.Enrollee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrolleeRepository extends JpaRepository<Enrollee, Long> {
    Optional<Enrollee> findByFirstName(String firstName); // this a custom query provided by java
}
