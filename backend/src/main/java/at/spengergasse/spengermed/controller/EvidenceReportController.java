package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Condition;
import at.spengergasse.spengermed.model.EvidenceReport;
import at.spengergasse.spengermed.repository.ConditionRespository;
import at.spengergasse.spengermed.repository.EvidenceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/evidencereport")
@CrossOrigin
public class EvidenceReportController {

    @Autowired
    private EvidenceReportRepository evidenceReportRepository;

    //get all conditions
    @GetMapping
    public @ResponseBody
    Iterable<EvidenceReport> getAllEvidenceReport(){
        return evidenceReportRepository.findAll();
    }

    //get condition by id
    @GetMapping("/{id}")
    public ResponseEntity<EvidenceReport> getEvidenceReport(@PathVariable String id) {
        return evidenceReportRepository
                .findById(id)
                .map(evidenceReport -> ResponseEntity.ok().body(evidenceReport))
                .orElse(ResponseEntity.notFound().build());
    }

    //New condition
    @PostMapping()
    public ResponseEntity<EvidenceReport> createEvidenceReport(@RequestBody EvidenceReport evidenceReport) {
        evidenceReport.setId(null); //ensures that that condition is new
        var saved = evidenceReportRepository.save(evidenceReport);
        return ResponseEntity.created(URI.create("api/evidencereport/" +saved.getId())).body(saved);
    }

    //Update Condition
    @PutMapping("/{id}")
    public ResponseEntity<EvidenceReport> updateEvidenceReport(@PathVariable(value = "id") String evidenceReportId, @RequestBody EvidenceReport evidenceReportDetails) {
        return evidenceReportRepository.findById(evidenceReportId).map(evidenceReport -> {
            evidenceReport.setIdentifier(evidenceReportDetails.getIdentifier());
            evidenceReport.setUrl(evidenceReportDetails.getUrl());
            evidenceReport.setSubject(evidenceReportDetails.getSubject());
            evidenceReport.setStatus(evidenceReportDetails.getStatus());
            evidenceReport.setUseContext(evidenceReportDetails.getUseContext());
            evidenceReport.setText(evidenceReportDetails.getText());


            EvidenceReport updatedEvidenceReport = evidenceReportRepository.save(evidenceReport);
            return ResponseEntity.ok(updatedEvidenceReport);
        }).orElseGet( () -> createEvidenceReport(evidenceReportDetails));
    }

    //Delete condition
    @DeleteMapping("/{id}")
    public ResponseEntity<EvidenceReport> deleteEvidenceReport(@PathVariable(value = "id") String evidenceReportId) {
        return evidenceReportRepository
                .findById(evidenceReportId)
                .map(
                        evidenceReport -> {
                            evidenceReportRepository.delete(evidenceReport);
                            return ResponseEntity.ok().<EvidenceReport>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
