package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.model.Patient;
import at.spengergasse.spengermed.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/encounter")
@CrossOrigin
public class EncounterController {

    @Autowired
    EncounterRepository encounterRepository;

    @GetMapping
    public @ResponseBody
    Iterable<Encounter> getAllEncounters() {
        return encounterRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encounter> getEncounter(@PathVariable String id) {
        return encounterRepository
                .findById(id)
                .map(encounter -> ResponseEntity.ok().body(encounter))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Encounter> createEncounter(@RequestBody Encounter encounter) {
        encounter.setId(null); //ensures that that item is new
        var saved = encounterRepository.save(encounter);
        return ResponseEntity.created(URI.create("/api/encounter/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encounter> updateEncounter(@PathVariable(value = "id") String encounterId, @RequestBody Encounter encounterDetails) {
        return encounterRepository.findById(encounterId).map(encounter -> {
            encounter.setAppointment(encounterDetails.getAppointment());
            encounter.setIdentifier(encounterDetails.getIdentifier());
            encounter.setDiagnosis(encounterDetails.getDiagnosis());
            encounter.setParticipant(encounterDetails.getParticipant());
            encounter.setEpisodeOfCare(encounterDetails.getEpisodeOfCare());
            encounter.setPartOf(encounterDetails.getPartOf());
            encounter.setPeriod(encounterDetails.getPeriod());
            encounter.setReasonReference(encounterDetails.getReasonReference());
            encounter.setStatus(encounterDetails.getStatus());
            encounter.setSubject(encounterDetails.getSubject());
            encounter.setType(encounterDetails.getType());

            Encounter updatedEncounter = encounterRepository.save(encounter);
            return ResponseEntity.ok(updatedEncounter);
        }).orElseGet(() -> createEncounter(encounterDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Encounter> deleteEncounter(@PathVariable(value = "id") String encounterId) {
        return encounterRepository.findById(encounterId).map(encounter -> {
            encounterRepository.delete(encounter);
            return ResponseEntity.ok().<Encounter>build();
        }).orElse(ResponseEntity.notFound().build());
    }


}
