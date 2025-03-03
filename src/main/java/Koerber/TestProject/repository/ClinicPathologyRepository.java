package Koerber.TestProject.repository;

import Koerber.TestProject.model.ClinicPathology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicPathologyRepository extends JpaRepository<ClinicPathology, Long> {
}
