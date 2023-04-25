package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Measure;
import at.spengergasse.spengermed.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/measure")
@CrossOrigin
public class MeasureController {

    @Autowired
    private MeasureRepository measureRepository;

    //get all Measures
    @GetMapping
    public @ResponseBody
    Iterable<Measure> getAllMeasures(){
        return measureRepository.findAll();
    }

    //get Measure by id
    @GetMapping("/{id}")
    public ResponseEntity<Measure> getMeasure(@PathVariable String id) {
        return measureRepository
                .findById(id)
                .map(Measure -> ResponseEntity.ok().body(Measure))
                .orElse(ResponseEntity.notFound().build());
    }

    //New Measure
    @PostMapping()
    public ResponseEntity<Measure> createMeasure(@RequestBody Measure measure) {
        measure.setId(null); //ensures that that Measure is new
        var saved = measureRepository.save(measure);
        return ResponseEntity.created(URI.create("api/measure/" +saved.getId())).body(saved);
    }

    //Update Measure
    @PutMapping("/{id}")
    public ResponseEntity<Measure> updateMeasure(@PathVariable(value = "id") String measureId, @RequestBody Measure measureDetails) {
        return measureRepository.findById(measureId).map(measure -> {
            measure.setDate(measureDetails.getDate());
            measure.setUrl(measureDetails.getUrl());
            measure.setVersion(measureDetails.getVersion());
            measure.setIdentifier(measureDetails.getIdentifier());
            measure.setSubjectReference(measureDetails.getSubjectReference());
            measure.setSubjectCodeableConcept(measureDetails.getSubjectCodeableConcept());
            measure.setGroup(measureDetails.getGroup());

            Measure updatedMeasure = measureRepository.save(measure);
            return ResponseEntity.ok(updatedMeasure);
        }).orElseGet( () -> createMeasure(measureDetails));
    }

    //Delete Measure
    @DeleteMapping("/{id}")
    public ResponseEntity<Measure> deleteMeasure(@PathVariable(value = "id") String measureId) {
        return measureRepository
                .findById(measureId)
                .map(
                        measure -> {
                            measureRepository.delete(measure);
                            return ResponseEntity.ok().<Measure>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
