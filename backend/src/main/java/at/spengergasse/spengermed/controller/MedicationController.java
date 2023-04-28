package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Medication;
import at.spengergasse.spengermed.model.Medication;
import at.spengergasse.spengermed.model.Patient;
import at.spengergasse.spengermed.model.Medication;
import at.spengergasse.spengermed.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/medication")
@CrossOrigin
public class MedicationController {

    @Autowired
    private MedicationRepository medicationRepository;

    @GetMapping
    public @ResponseBody
    Iterable<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedication(@PathVariable String id) {
        return medicationRepository
                .findById(id)
                .map(medication -> ResponseEntity.ok().body(medication))
                .orElse(ResponseEntity.notFound().build());
    }

    //New medication
    @PostMapping()
    public ResponseEntity<Medication> createMedication(/*@Valid*/ @RequestBody Medication medication) {
        medication.setId(null); //ensures that that medication is new
        var saved = medicationRepository.save(medication);
        return ResponseEntity.created(URI.create("/api/medication/" + saved.getId())).body(saved);
    }

    //Update medication
    @PutMapping("/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable(value = "id") String medicationId, /*@Valid*/ @RequestBody Medication medicationDetails) {
        return medicationRepository.findById(medicationId).map(medication -> {
            medication.setCode(medicationDetails.getCode());
            medication.setBatch(medicationDetails.getBatch());
            medication.setStatus(medicationDetails.getStatus());
            medication.setIdentifier(medicationDetails.getIdentifier());
            medication.setIngredient(medicationDetails.getIngredient());
            medication.setTotalVolume(medicationDetails.getTotalVolume());


            Medication updatedmedication = medicationRepository.save(medication);
            return ResponseEntity.ok(updatedmedication);
        }).orElseGet(() -> createMedication(medicationDetails));
    }

    //Delete medication
    @DeleteMapping("/{id}")
    public ResponseEntity<Medication> deleteMedication(@PathVariable(value = "id") String medicationId) {
        return medicationRepository
                .findById(medicationId)
                .map(
                        medication -> {
                            medicationRepository.delete(medication);
                            return ResponseEntity.ok().<Medication>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }

}