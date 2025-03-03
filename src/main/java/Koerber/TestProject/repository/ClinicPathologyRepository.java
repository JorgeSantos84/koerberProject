package Koerber.TestProject.repository;

import Koerber.TestProject.model.ClinicPathology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicPathologyRepository extends JpaRepository<ClinicPathology, Long> {
}
