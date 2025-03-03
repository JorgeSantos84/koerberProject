package Koerber.TestProject.repository;

import Koerber.TestProject.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {


}
