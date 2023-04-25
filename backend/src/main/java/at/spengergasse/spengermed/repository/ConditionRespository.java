package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Condition;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConditionRespository extends PagingAndSortingRepository<Condition, String> {
}
