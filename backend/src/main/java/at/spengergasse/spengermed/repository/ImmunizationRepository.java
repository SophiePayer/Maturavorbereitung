package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Immunization;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImmunizationRepository extends PagingAndSortingRepository<Immunization, String> {
}
