package at.spengergasse.spengermed.repository;


import at.spengergasse.spengermed.model.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PatientRepository extends PagingAndSortingRepository<Patient, String> {

}
