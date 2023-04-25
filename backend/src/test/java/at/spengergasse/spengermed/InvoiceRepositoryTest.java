package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.InvoiceRepository;
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
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Test
    @Transactional
    public void testSaveAndLoadOneInvoice(){

        Invoice in = returnOneInvoice();

        Invoice savedIn = invoiceRepository.save(in);
        Invoice loadedIn = invoiceRepository.findById(savedIn.getId()).get();

        assertNotNull(loadedIn);
        assertTrue(CollectionUtils.isEqualCollection(in.getLineitem(), loadedIn.getLineitem()));
    }

    @Test
    @Transactional
    public void testDeleteOneInvoice(){
        Invoice in = returnOneInvoice();
        Invoice savedIn = invoiceRepository.save(in);

        invoiceRepository.deleteById(savedIn.getId());
        assertFalse(invoiceRepository.findById(savedIn.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneInvoice(){
        Invoice savedIn = invoiceRepository.save(new Invoice());

        Invoice in = returnOneInvoice();
        in.setId(savedIn.getId());
        invoiceRepository.save(in);
        Invoice updatedIn = invoiceRepository.findById(in.getId()).get();

        assertNotNull(updatedIn.getLineitem());
        assertNotNull(updatedIn.getText());
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
    public static Invoice returnOneInvoice() {

        List<LineItem> lineitems = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();

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

        codings.add(Coding.builder()
                .display("SS")
                .code("Sozialversicherung")
                .build());

        lineitems.add(LineItem.builder()
                        .chargeItemCodableConcept(CodeableConcept.builder()
                                .coding(codings)
                                .text("<div></div>")
                                .build())
                        .chargeItemReference(Reference.builder()
                                .display("Mutter")
                                .type("Person")
                                .identifier(parentIdentifier)
                                .reference("Mutter")
                                .build())
                        .sequence(34)
                        .type(LineItem.TypeCode.subcharge)
                .build());

        Invoice in = Invoice.builder()
                .lineitem(lineitems)
                .build();

        Narrative text = new Narrative(Narrative.NarrativeCode.additional, "<div></div>");
        in.setText(text);

        return in;
    }
}
