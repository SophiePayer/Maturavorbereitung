package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.model.EvidenceReport;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EvidenceReportRepository extends PagingAndSortingRepository<EvidenceReport, String> {
}
