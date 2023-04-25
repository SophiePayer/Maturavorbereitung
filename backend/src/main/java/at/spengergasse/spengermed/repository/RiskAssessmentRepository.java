package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Practitioner;
import at.spengergasse.spengermed.model.RiskAssessment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RiskAssessmentRepository extends PagingAndSortingRepository<RiskAssessment, String> {
}
