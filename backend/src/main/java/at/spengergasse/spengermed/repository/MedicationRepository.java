package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Medication;
import at.spengergasse.spengermed.model.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MedicationRepository extends PagingAndSortingRepository<Medication, String> {
}
