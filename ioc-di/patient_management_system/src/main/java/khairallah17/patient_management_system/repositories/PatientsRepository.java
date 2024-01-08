package khairallah17.patient_management_system.repositories;

import khairallah17.patient_management_system.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientsRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByName(String keyword, Pageable pageable);
}
