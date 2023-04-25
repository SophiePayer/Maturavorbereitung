package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, String> {
}
