package Koerber.TestProject.repository;

import Koerber.TestProject.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsultRepository extends JpaRepository<Consult, Long> {


}
