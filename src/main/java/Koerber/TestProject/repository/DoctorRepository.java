package Koerber.TestProject.repository;


import Koerber.TestProject.model.ClinicSpecialty;
import Koerber.TestProject.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
