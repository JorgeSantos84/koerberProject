package Koerber.TestProject.repository;


import Koerber.TestProject.model.ClinicSpecialty;
import Koerber.TestProject.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
