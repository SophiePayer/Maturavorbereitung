package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Observation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ObservationRepository extends PagingAndSortingRepository<Observation, String> {
}
