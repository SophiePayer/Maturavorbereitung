package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.PractitionerRepository;
import at.spengergasse.spengermed.repository.RiskAssessmentRepository;
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
public class RiskAssessmentRepositoryTest {

    @Autowired
    RiskAssessmentRepository riskAssessmentRepository;

    @Test
    @Transactional
    public void testSaveAndLoadOneRiskAssessment(){

        RiskAssessment ra = returnOneRiskAssessment();

        RiskAssessment savedRa = riskAssessmentRepository.save(ra);
        RiskAssessment loadedRa = riskAssessmentRepository.findById(savedRa.getId()).get();

        assertNotNull(loadedRa);

        assertEquals(ra.getStatus(),loadedRa.getStatus());
        assertEquals(ra.getParent(),loadedRa.getParent());
        assertEquals(ra.getText(), loadedRa.getText());

        assertTrue(CollectionUtils.isEqualCollection(ra.getPredictions(), loadedRa.getPredictions()));
        assertTrue(CollectionUtils.isEqualCollection(ra.getIdentifier(), loadedRa.getIdentifier()));
    }

    @Test
    @Transactional
    public void testDeleteOneRiskAssessment(){
        RiskAssessment ra = returnOneRiskAssessment();
        RiskAssessment savedRa = riskAssessmentRepository.save(ra);

        riskAssessmentRepository.deleteById(savedRa.getId());
        assertFalse(riskAssessmentRepository.findById(savedRa.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneRiskAssessment(){
        RiskAssessment savedRa = riskAssessmentRepository.save(new RiskAssessment());

        RiskAssessment ra = returnOneRiskAssessment();
        ra.setId(savedRa.getId());
        riskAssessmentRepository.save(ra);
        RiskAssessment updatedRa = riskAssessmentRepository.findById(ra.getId()).get();

        assertNotNull(updatedRa.getText());
        assertNotNull(updatedRa.getPredictions());
        assertNotNull(ra.getIdentifier());
        assertNotNull(ra.getStatus());
        assertNotNull(ra.getParent());;
    }
    public static RiskAssessment returnOneRiskAssessment() {

        List<Coding> codings = new ArrayList<>();
        List<Prediction> predictions = new ArrayList<>();
        List<Identifier> identifiers = new ArrayList<>();
        List<Coding> predictioncodings = new ArrayList<>();
        List<Coding> identifiercodings = new ArrayList<>();

        predictioncodings.add(Coding.builder()
                        .code("Sozialversicherungsnummer")
                        .display("SS")
                .build());

        identifiercodings.add(Coding.builder()
                        .code("Sozialversicherungsnummer")
                        .display("SS")
                .build());

        identifiers.add(Identifier.builder()
                .period(new Period(LocalDateTime.of(2004,03,26,00,00),LocalDateTime.now()))
                .value("12345334")
                .use(Identifier.UseCode.official)
                .system("urn:ietf:rfc:34563")
                .type(CodeableConcept.builder()
                        .coding(identifiercodings)
                        .text("<div></div>")
                        .build())
                .build());

        codings.add(Coding.builder()
                .display("SS")
                .code("Sozialversicherung")
                .build());

        predictions.add(Prediction.builder()
                        .outcome(CodeableConcept.builder()
                                .text("Verletzung")
                                .coding(predictioncodings)
                                .build())
                        .prohabilityDecimal(90)
                        .prohabilityRange(60)
                .build());


        Identifier parentIdentifier = Identifier.builder()
                .value("1234567890")
                .use(Identifier.UseCode.official)
                .system("urn:ietf:rfc:34563")
                .type(CodeableConcept.builder()
                        .coding(codings)
                        .text("<div></div>")
                        .build())
                .period(new Period(LocalDateTime.of(2004,03,26,00,00),LocalDateTime.now()))
                .build();

        RiskAssessment ra = RiskAssessment.builder()
                .parent(Reference.builder()
                        .display("Mutter")
                        .type("Person")
                        .identifier(parentIdentifier)
                        .reference("Mutter")
                        .build())
                .predictions(predictions)
                .status(RiskAssessment.StatusCode.registered)
                .identifier(identifiers)
                .build();

        Narrative text = new Narrative(Narrative.NarrativeCode.additional, "<div></div>");
        ra.setText(text);

        return ra;
    }
}
