package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.RiskAssessment;

import at.spengergasse.spengermed.repository.RiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/riskassessment")
@CrossOrigin
public class RiskAssessmentController {

    @Autowired
    private RiskAssessmentRepository riskAssessmentRepository;

    @GetMapping
    public @ResponseBody
    Iterable<RiskAssessment> getAllRiskAssessments() {
        return riskAssessmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiskAssessment> getRiskAssessment(@PathVariable String id) {
        return riskAssessmentRepository
                .findById(id)
                .map(riskAssessment -> ResponseEntity.ok().body(riskAssessment))
                .orElse(ResponseEntity.notFound().build());
    }

    //New RiskAssessment
    @PostMapping()
    public ResponseEntity<RiskAssessment> createRiskAssessment(@Valid @RequestBody RiskAssessment riskAssessment) {
        riskAssessment.setId(null); //ensures that RiskAssessment is new
        var saved = riskAssessmentRepository.save(riskAssessment);
        return ResponseEntity.created(URI.create("/api/riskassessment/" + saved.getId())).body(saved);
    }

    //Update RiskAssessment
    @PutMapping("/{id}")
    public ResponseEntity<RiskAssessment> updateRiskAssessment(@PathVariable(value = "id") String riskAssessmentId, @RequestBody RiskAssessment riskAssessmentDetails) {
        return riskAssessmentRepository.findById(riskAssessmentId).map(riskAssessment -> {
            riskAssessment.setParent(riskAssessmentDetails.getParent());
            riskAssessment.setPredictions(riskAssessment.getPredictions());
            riskAssessment.setStatus(riskAssessmentDetails.getStatus());
            riskAssessment.setIdentifier(riskAssessmentDetails.getIdentifier());

            RiskAssessment updatedRiskAssessment = riskAssessmentRepository.save(riskAssessment);
            return ResponseEntity.ok(updatedRiskAssessment);
        }).orElseGet(() -> createRiskAssessment(riskAssessmentDetails));
    }

    //Delete RiskAssessment
    @DeleteMapping("/{id}")
    public ResponseEntity<RiskAssessment> deleteRiskAssessment(@PathVariable(value = "id") String riskAssessmentId) {
        return riskAssessmentRepository
                .findById(riskAssessmentId)
                .map(
                        riskAssessment -> {
                            riskAssessmentRepository.delete(riskAssessment);
                            return ResponseEntity.ok().<RiskAssessment>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
