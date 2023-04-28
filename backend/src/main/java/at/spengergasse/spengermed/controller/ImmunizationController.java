package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Immunization;
import at.spengergasse.spengermed.repository.ImmunizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/immunization")
@CrossOrigin
public class ImmunizationController {
    @Autowired
    private ImmunizationRepository immunizationRepository;

    @GetMapping
    public @ResponseBody
    Iterable<Immunization> getAllImmunizations() {
        return immunizationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Immunization> getImmunization(@PathVariable String id) {
        return immunizationRepository
                .findById(id)
                .map(immunization -> ResponseEntity.ok().body(immunization))
                .orElse(ResponseEntity.notFound().build());
    }

    //New Immunization
    @PostMapping()
    public ResponseEntity<Immunization> createImmunization(@Valid @RequestBody Immunization immunization) {
        immunization.setId(null); //ensures that Immunization is new
        var saved = immunizationRepository.save(immunization);
        return ResponseEntity.created(URI.create("/api/immunization/" + saved.getId())).body(saved);
    }

    //Update Immunization
    @PutMapping("/{id}")
    public ResponseEntity<Immunization> updateImmunization(@PathVariable(value = "id") String immunizationId, @RequestBody Immunization immunizationDetails) {
        return immunizationRepository.findById(immunizationId).map(immunization -> {
            immunization.setExpirationdate(immunizationDetails.getExpirationdate());
            immunization.setIotnumber(immunizationDetails.getIotnumber());
            immunization.setPatient(immunizationDetails.getPatient());
            immunization.setOccurrenceString(immunizationDetails.getOccurrenceString());
            immunization.setOccurrenceDateTime(immunizationDetails.getOccurrenceDateTime());
            immunization.setText(immunizationDetails.getText());


            Immunization updatedImmunization = immunizationRepository.save(immunization);
            return ResponseEntity.ok(updatedImmunization);
        }).orElseGet(() -> createImmunization(immunizationDetails));
    }

    //Delete Immunization
    @DeleteMapping("/{id}")
    public ResponseEntity<Immunization> deleteImmunization(@PathVariable(value = "id") String immunizationId) {
        return immunizationRepository
                .findById(immunizationId)
                .map(
                        immunization -> {
                            immunizationRepository.delete(immunization);
                            return ResponseEntity.ok().<Immunization>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
