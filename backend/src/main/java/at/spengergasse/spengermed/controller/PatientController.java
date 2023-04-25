package at.spengergasse.spengermed.controller;


import at.spengergasse.spengermed.model.Patient;
import at.spengergasse.spengermed.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/patient")
@CrossOrigin
public class PatientController {

  @Autowired
  private PatientRepository patientRepository;

  @GetMapping
  public @ResponseBody
  Iterable<Patient> getAllPatients() {
    return patientRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Patient> getPatient(@PathVariable String id) {
    return patientRepository
            .findById(id)
            .map(patient -> ResponseEntity.ok().body(patient))
            .orElse(ResponseEntity.notFound().build());
  }

  //New Patient
  @PostMapping()
  public ResponseEntity<Patient> createPatient(/*@Valid*/ @RequestBody Patient patient) {
    patient.setId(null); //ensures that that patient is new
    var saved = patientRepository.save(patient);
    return ResponseEntity.created(URI.create("/api/patient/" + saved.getId())).body(saved);
  }

  //Update Patient
  @PutMapping("/{id}")
  public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") String patientId, /*@Valid*/ @RequestBody Patient patientDetails) {
    return patientRepository.findById(patientId).map(patient -> {
      patient.setActive(patientDetails.getActive());
      patient.setGender(patientDetails.getGender());
      patient.setIdentifier(patientDetails.getIdentifier());
      patient.setName(patientDetails.getName());
      patient.setAddress(patientDetails.getAddress());
      patient.setBirthDate(patientDetails.getBirthDate());
      patient.setText(patientDetails.getText());
      patient.setTelecom(patientDetails.getTelecom());
      patient.setDeceasedDateTime(patientDetails.getDeceasedDateTime());
      patient.setDeceasedBoolean(patientDetails.getDeceasedBoolean());
      patient.setPhoto(patientDetails.getPhoto());
      patient.setGeneralPractitioner(patientDetails.getGeneralPractitioner());

      Patient updatedPatient = patientRepository.save(patient);
      return ResponseEntity.ok(updatedPatient);
    }).orElseGet(() -> createPatient(patientDetails));
  }

  //Delete Patient
  @DeleteMapping("/{id}")
  public ResponseEntity<Patient> deletePatient(@PathVariable(value = "id") String patientId) {
    return patientRepository
            .findById(patientId)
            .map(
                    patient -> {
                      patientRepository.delete(patient);
                      return ResponseEntity.ok().<Patient>build();
                    })
            .orElse(ResponseEntity.notFound().build());
  }

  /*//Fangen, wenn die Validation falsch ist --> Exceptionhandler
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> onConstraintValidationException(ConstraintViolationException e) {
    Map<String, String> errors = new HashMap<>();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      errors.put(violation.getPropertyPath().toString(), violation.getMessage());
    }
    return errors;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return errors;
  }*/
}

