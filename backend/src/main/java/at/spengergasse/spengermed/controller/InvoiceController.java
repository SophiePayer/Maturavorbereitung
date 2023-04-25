package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Invoice;
import at.spengergasse.spengermed.model.Practitioner;
import at.spengergasse.spengermed.repository.InvoiceRepository;
import at.spengergasse.spengermed.repository.PractitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@CrossOrigin
@RequestMapping("api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    //get all practitioners
    @GetMapping
    public @ResponseBody
    Iterable<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

    //get practitioner by id
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable String id) {
        return invoiceRepository
                .findById(id)
                .map(invoice -> ResponseEntity.ok().body(invoice))
                .orElse(ResponseEntity.notFound().build());
    }

    //New Invoice
    @PostMapping()
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice) {
        invoice.setId(null); //ensures that that invoice is new
        var saved = invoiceRepository.save(invoice);
        return ResponseEntity.created(URI.create("api/invoice/" +saved.getId())).body(saved);
    }

    //Update Invoice
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable(value = "id") String invoiceId, @RequestBody Invoice invoiceDetails) {
        return invoiceRepository.findById(invoiceId).map(invoice -> {
            invoice.setLineitem(invoiceDetails.getLineitem());

            Invoice updatedInvoice = invoiceRepository.save(invoice);
            return ResponseEntity.ok(updatedInvoice);
        }).orElseGet( () -> createInvoice(invoiceDetails));
    }

    //Delete Invoice
    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable(value = "id") String invoiceId) {
        return invoiceRepository
                .findById(invoiceId)
                .map(
                        invoice -> {
                            invoiceRepository.delete(invoice);
                            return ResponseEntity.ok().<Invoice>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
