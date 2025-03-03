package Koerber.TestProject.repository;

import Koerber.TestProject.model.ClinicSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicSpecialtyRepository extends JpaRepository<ClinicSpecialty, Long> {
}
