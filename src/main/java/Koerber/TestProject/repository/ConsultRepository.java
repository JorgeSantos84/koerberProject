package Koerber.TestProject.repository;

import Koerber.TestProject.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {

    List<Consult> findByPatient_IdPatient(Long idPatient);

    @Query("SELECT DISTINCT d.specialty.id, d.specialty.nameSpecialty, c.patient.id " +
            "FROM Consult c " +
            "JOIN c.doctor d")
    List<Object[]> findUniqueSpecialtyAndPatient();
}
