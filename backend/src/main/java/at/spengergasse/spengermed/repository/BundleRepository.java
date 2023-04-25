package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Bundle;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BundleRepository extends PagingAndSortingRepository<Bundle, String> {
}
