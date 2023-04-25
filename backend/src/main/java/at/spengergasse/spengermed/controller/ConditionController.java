package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Condition;
import at.spengergasse.spengermed.repository.ConditionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/condition")
@CrossOrigin
public class ConditionController {

    @Autowired
    private ConditionRespository conditionRespository;

    //get all conditions
    @GetMapping
    public @ResponseBody
    Iterable<Condition> getAllConditions(){
        return conditionRespository.findAll();
    }

    //get condition by id
    @GetMapping("/{id}")
    public ResponseEntity<Condition> getCondition(@PathVariable String id) {
        return conditionRespository
                .findById(id)
                .map(condition -> ResponseEntity.ok().body(condition))
                .orElse(ResponseEntity.notFound().build());
    }

    //New condition
    @PostMapping()
    public ResponseEntity<Condition> createCondition(@RequestBody Condition condition) {
        condition.setId(null); //ensures that that condition is new
        var saved = conditionRespository.save(condition);
        return ResponseEntity.created(URI.create("api/condition/" +saved.getId())).body(saved);
    }

    //Update Condition
    @PutMapping("/{id}")
    public ResponseEntity<Condition> updateCondition(@PathVariable(value = "id") String conditionId, @RequestBody Condition conditionDetails) {
        return conditionRespository.findById(conditionId).map(condition -> {
            condition.setBodySite(conditionDetails.getBodySite());
            condition.setEncounter(conditionDetails.getEncounter());
            condition.setCode(conditionDetails.getCode());
            condition.setIdentifiers(conditionDetails.getIdentifiers());
            condition.setSubject(conditionDetails.getSubject());
            condition.setCategory(conditionDetails.getCategory());
            condition.setSeverity(conditionDetails.getSeverity());
            condition.setClinicalStatus(conditionDetails.getClinicalStatus());
            condition.setVerificationStatus(conditionDetails.getVerificationStatus());

            Condition updatedcondition = conditionRespository.save(condition);
            return ResponseEntity.ok(updatedcondition);
        }).orElseGet( () -> createCondition(conditionDetails));
    }

    //Delete condition
    @DeleteMapping("/{id}")
    public ResponseEntity<Condition> deleteCondition(@PathVariable(value = "id") String conditionId) {
        return conditionRespository
                .findById(conditionId)
                .map(
                        condition -> {
                            conditionRespository.delete(condition);
                            return ResponseEntity.ok().<Condition>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
