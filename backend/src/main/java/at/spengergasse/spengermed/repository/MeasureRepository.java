package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Measure;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MeasureRepository extends PagingAndSortingRepository<Measure, String> {
}
