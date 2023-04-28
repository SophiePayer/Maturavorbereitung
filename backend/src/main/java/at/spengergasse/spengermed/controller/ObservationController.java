package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Observation;
import at.spengergasse.spengermed.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
@RequestMapping(path = "/api/observation")
@CrossOrigin
public class ObservationController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObservationRepository observationRepository;

    //get all Observations
    @GetMapping
    public @ResponseBody
    Iterable<Observation> getAllObservations(){
        return observationRepository.findAll();
    }

    //get Observation by id
    @GetMapping("/{id}")
    public ResponseEntity<Observation> getObservation(@PathVariable String id) {
        return observationRepository
                .findById(id)
                .map(Observation -> ResponseEntity.ok().body(Observation))
                .orElse(ResponseEntity.notFound().build());
    }

    //New Observation
    @PostMapping()
    public ResponseEntity<Observation> createObservation(@RequestBody Observation observation) {
        observation.setId(null); //ensures that that Observation is new
        var saved = observationRepository.save(observation);
        return ResponseEntity.created(URI.create("api/observation/" +saved.getId())).body(saved);
    }

    //Update Observation
    @PutMapping("/{id}")
    public ResponseEntity<Observation> updateObservation(@PathVariable(value = "id") String observationId, @RequestBody Observation observationDetails) {
        return observationRepository.findById(observationId).map(observation -> {
            observation.setObservationDefinition(observationDetails.getObservationDefinition());
            observation.setCanonical(observationDetails.getCanonical());
            observation.setBasedon(observationDetails.getBasedon());
            observation.setIdentifier(observationDetails.getIdentifier());
            observation.setInstantiatesReference(observationDetails.getInstantiatesReference());
            observation.setTriggeredby(observationDetails.getTriggeredby());

            Observation updatedObservation = observationRepository.save(observation);
            return ResponseEntity.ok(updatedObservation);
        }).orElseGet( () -> createObservation(observationDetails));
    }

    //Delete Observation
    @DeleteMapping("/{id}")
    public ResponseEntity<Observation> deleteObservation(@PathVariable(value = "id") String observationId) {
        return observationRepository
                .findById(observationId)
                .map(
                        observation -> {
                            observationRepository.delete(observation);
                            return ResponseEntity.ok().<Observation>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
