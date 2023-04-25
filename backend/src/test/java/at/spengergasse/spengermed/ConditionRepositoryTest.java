package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.ConditionRespository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ConditionRepositoryTest {
    @Autowired
    ConditionRespository conditionRepository;

    @Test
    @Transactional
    public void testSaveAndLoadOneCondition(){

        Condition con = returnOneCondition();

        Condition savedCon = conditionRepository.save(con);
        Condition loadedCon = conditionRepository.findById(savedCon.getId()).get();

        assertNotNull(loadedCon);

        assertEquals(con.getCode(), loadedCon.getCode());
        assertEquals(con.getEncounter(), loadedCon.getEncounter());
        assertEquals(con.getClinicalStatus(), loadedCon.getClinicalStatus());
        assertEquals(con.getSeverity(), loadedCon.getSeverity());
        assertEquals(con.getSubject(), loadedCon.getSubject());
        assertEquals(con.getVerificationStatus(), loadedCon.getVerificationStatus());
        assertEquals(con.getText(), loadedCon.getText());

        assertTrue(CollectionUtils.isEqualCollection(con.getIdentifiers(),loadedCon.getIdentifiers()));
        assertTrue(CollectionUtils.isEqualCollection(con.getBodySite(),loadedCon.getBodySite()));
        assertTrue(CollectionUtils.isEqualCollection(con.getCategory(), loadedCon.getCategory()));
    }

    @Test
    @Transactional
    public void testDeleteOneCondition(){
        Condition con = returnOneCondition();
        Condition savedCon = conditionRepository.save(con);

        conditionRepository.deleteById(savedCon.getId());
        assertFalse(conditionRepository.findById(savedCon.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneCondition(){
        Condition savedCon = conditionRepository.save(new Condition());

        Condition con = returnOneCondition();
        con.setId(savedCon.getId());
        conditionRepository.save(con);
        Condition updatedCon = conditionRepository.findById(con.getId()).get();

        assertNotNull(updatedCon.getText());
        assertNotNull(updatedCon.getBodySite());
        assertNotNull(updatedCon.getVerificationStatus());
        assertNotNull(updatedCon.getCategory());
        assertNotNull(updatedCon.getIdentifiers());
        assertNotNull(updatedCon.getCode());
        assertNotNull(updatedCon.getSeverity());
        assertNotNull(updatedCon.getEncounter());
        assertNotNull(updatedCon.getClinicalStatus());
        assertNotNull(updatedCon.getSubject());
    }

    public static Condition returnOneCondition() {

        List<Coding> clinStats = new ArrayList<>();
        List<CodeableConcept> categories = new ArrayList<>();
        List<Coding>veriStats = new ArrayList<>();
        List<Coding> serverities = new ArrayList<>();
        List<CodeableConcept> bodySites = new ArrayList<>();
        List<Identifier> identifiers = new ArrayList<>();
        List<Coding> codes = new ArrayList<>();
        List<Coding> categorieCoding = new ArrayList<>();
        List<Coding> bodysiteCoding = new ArrayList<>();


        codes.add(Coding.builder()
                        .code("386661006")
                        .display("Fever")
                        .system("http://snomed.info/sct")
                .build());

        identifiers.add(Identifier.builder()
                        .value("12345")
                .build());

        bodysiteCoding.add(Coding.builder()
                        .display("Left external Ear Structure")
                        .system("http://snomed.info/sct")
                        .code("49521004")
                .build());

        bodySites.add(CodeableConcept.builder()
                        .text("<div></div>")
                        .coding(bodysiteCoding)
                .build());

        serverities.add(Coding.builder()
                        .code("255604002")
                        .system("http://snomed.info/sct")
                        .display("Mild")
                .build());

        veriStats.add(Coding.builder()
                        .system("http://terminology.hl7.org/CodeSystem/condition-ver-status")
                        .code("confirmed")
                .build());

        clinStats.add(Coding.builder()
                        .code("active")
                        .system("http://terminology.hl7.org/CodeSystem/condition-clinical")
                .build());

        categorieCoding.add(Coding.builder()
                        .system("http://terminology.hl7.org/CodeSystem/condition-category")
                        .code("problem-list-item")
                        .display("Problem List Item")
                .build());

        categories.add(CodeableConcept.builder()
                        .coding(categorieCoding)
                        .text("<div></div>")
                .build());

        Condition con = Condition.builder()
                .clinicalStatus(CodeableConcept.builder()
                        .coding(clinStats)
                        .text("<div></div>")
                        .build())
                .verificationStatus(CodeableConcept.builder()
                        .text("<div></div>")
                        .coding(veriStats)
                        .build())
                .category(categories)
                .severity(CodeableConcept.builder()
                        .coding(serverities)
                        .build())
                .bodySite(bodySites)
                .identifiers(identifiers)
                .encounter(Reference.builder()
                        .build())
                .code(CodeableConcept.builder()
                        .text("Asthma")
                        .coding(codes)
                        .build())
                .subject(Reference.builder()
                        .display("Patient/00000000-0000-0000-0001")
                        .type("Patient")
                        .build())
                .encounter(Reference.builder()
                        .display("Encounter/00000000-0000-1133-0000")
                        .type("Encounter")
                        .build())
                .build();

        Narrative text = new Narrative(Narrative.NarrativeCode.additional, "<div></div>");
        con.setText(text);

        return con;
    }
}
