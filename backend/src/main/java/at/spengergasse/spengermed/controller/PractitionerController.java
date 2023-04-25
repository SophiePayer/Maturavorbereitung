package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Patient;
import at.spengergasse.spengermed.model.Practitioner;
import at.spengergasse.spengermed.repository.PractitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/practitioner")
@CrossOrigin
public class PractitionerController {

    @Autowired
    private PractitionerRepository practitionerRepository;

    //get all practitioners
    @GetMapping
    public @ResponseBody
    Iterable<Practitioner> getAllPractitioners(){
        return practitionerRepository.findAll();
    }

    //get practitioner by id
    @GetMapping("/{id}")
    public ResponseEntity<Practitioner> getPractitioner(@PathVariable String id) {
        return practitionerRepository
                .findById(id)
                .map(practitioner -> ResponseEntity.ok().body(practitioner))
                .orElse(ResponseEntity.notFound().build());
    }

    //New Practitioner
    @PostMapping()
    public ResponseEntity<Practitioner> createPractitioner(@RequestBody Practitioner practitioner) {
        practitioner.setId(null); //ensures that that patient is new
        var saved = practitionerRepository.save(practitioner);
        return ResponseEntity.created(URI.create("api/practitioner/" +saved.getId())).body(saved);
    }

    //Update Practitioner
    @PutMapping("/{id}")
    public ResponseEntity<Practitioner> updatePractitioner(@PathVariable(value = "id") String practitionerId, @RequestBody Practitioner practitionerDetails) {
        return practitionerRepository.findById(practitionerId).map(practitioner -> {
            practitioner.setActive(practitionerDetails.getActive());
            practitioner.setAddress(practitionerDetails.getAddress());
            practitioner.setCommunication(practitionerDetails.getCommunication());
            practitioner.setBirthdate(practitionerDetails.getBirthdate());
            practitioner.setGender(practitionerDetails.getGender());
            practitioner.setIdentifier(practitionerDetails.getIdentifier());
            practitioner.setPhoto(practitionerDetails.getPhoto());
            practitioner.setName(practitionerDetails.getName());
            practitioner.setQualification(practitionerDetails.getQualification());
            practitioner.setTelecom(practitionerDetails.getTelecom());

            Practitioner updatedPractitioner = practitionerRepository.save(practitioner);
            return ResponseEntity.ok(updatedPractitioner);
        }).orElseGet( () -> createPractitioner(practitionerDetails));
    }

    //Delete Practitioner
    @DeleteMapping("/{id}")
    public ResponseEntity<Practitioner> deletePractitioner(@PathVariable(value = "id") String practitionerId) {
        return practitionerRepository
                .findById(practitionerId)
                .map(
                        practitioner -> {
                            practitionerRepository.delete(practitioner);
                            return ResponseEntity.ok().<Practitioner>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
