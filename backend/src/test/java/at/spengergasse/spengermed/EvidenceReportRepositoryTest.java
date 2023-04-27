package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.EvidenceReportRepository;
import at.spengergasse.spengermed.repository.InvoiceRepository;
import at.spengergasse.spengermed.repository.RiskAssessmentRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EvidenceReportRepositoryTest {

    @Autowired
    EvidenceReportRepository evidenceReportRepository;

    @Test
    @Transactional
    public void testSaveAndLoadOneEvidenceReport(){

        EvidenceReport in = returnOneEvidenceReport();

        EvidenceReport savedIn = evidenceReportRepository.save(in);
        EvidenceReport loadedIn = evidenceReportRepository.findById(savedIn.getId()).get();

        assertNotNull(loadedIn);
        assertEquals(in.getSubject(), loadedIn.getSubject());
        assertEquals(in.getUrl(), loadedIn.getUrl());
        assertEquals(in.getStatus(), loadedIn.getStatus());
        assertEquals(in.getText(), loadedIn.getText());


        assertTrue(CollectionUtils.isEqualCollection(in.getIdentifier(), loadedIn.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(in.getUseContext(), loadedIn.getUseContext()));


    }

    @Test
    @Transactional
    public void testDeleteOneEvidenceReport(){
        EvidenceReport in = returnOneEvidenceReport();
        EvidenceReport savedIn = evidenceReportRepository.save(in);

        evidenceReportRepository.deleteById(savedIn.getId());
        assertFalse(evidenceReportRepository.findById(savedIn.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneEvidenceReport(){
        EvidenceReport savedIn = evidenceReportRepository.save(new EvidenceReport());

        EvidenceReport in = returnOneEvidenceReport();
        in.setId(savedIn.getId());
        evidenceReportRepository.save(in);
        EvidenceReport updatedIn = evidenceReportRepository.findById(in.getId()).get();

        assertNotNull(updatedIn.getUseContext());
        assertNotNull(updatedIn.getUrl());
        assertNotNull(updatedIn.getIdentifier());
        assertNotNull(updatedIn.getStatus());
        assertNotNull(updatedIn.getSubject());
        assertNotNull(updatedIn.getText());
    }

    public static EvidenceReport returnOneEvidenceReport() {
        Subject subject = new Subject();
        List<Characteristic> characteristics = new ArrayList<>();
        Characteristic characteristic = new Characteristic();

        List<Coding> codings = new ArrayList<>();
        List<Identifier> identifiers = new ArrayList<>();
        List<UsageContext> usageContexts = new ArrayList<>();
        Coding coding = new Coding();
        coding.setSystem("test");
        coding.setDisplay("ok");
        UsageContext usageContext = new UsageContext();
        usageContext.setCode(coding);
        usageContexts.add(usageContext);


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
        identifiers.add(parentIdentifier);
        codings.add(Coding.builder()
                .display("SS")
                .code("Sozialversicherung")
                .build());

        Reference reference = Reference.builder()
                .display("test")
                .identifier(parentIdentifier)
                .type("typ")
                .reference("reference")
                .build();

        CodeableConcept ccType = CodeableConcept.builder()
                .coding(codings)
                .text("<div></div>")
                .build();

        usageContext.setValueReference(reference);
        usageContext.setValueCodeableConcept(ccType);


        characteristic.setCode(coding);
        characteristic.setValueReference(reference);
        characteristic.setValueCodeableConcept(ccType);
        characteristics.add(characteristic);
        subject.setCharacteristic(characteristics);
        List<Annotation> annotations = new ArrayList<>();
        Annotation annotation = new Annotation();
        annotation.setText("aua");
        annotations.add(annotation);
        subject.setNote(annotations);
        EvidenceReport e = EvidenceReport.builder()
                .url(URI.create("https://spengergasse.at"))
                .subject(subject)
                .identifier(identifiers)
                .useContext(usageContexts)
                .status(EvidenceReport.StatusCode.active)
                .build();

        Narrative text = new Narrative(Narrative.NarrativeCode.additional, "<div></div>");
        e.setText(text);

        return e;

    }
}
