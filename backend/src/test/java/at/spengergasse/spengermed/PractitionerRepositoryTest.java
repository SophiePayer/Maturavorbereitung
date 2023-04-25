package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.PractitionerRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PractitionerRepositoryTest {

    @Autowired
    PractitionerRepository practitionerRepository;

    @Test
    @Transactional
    public void testSaveAndLoadOnePractitioner(){

        Practitioner pra = returnOnePractitioner();

        Practitioner savedPra = practitionerRepository.save(pra);
        Practitioner loadedPra = practitionerRepository.findById(savedPra.getId()).get();

        assertNotNull(loadedPra);

        assertEquals(pra.getActive(), loadedPra.getActive());
        assertEquals(pra.getBirthdate(), loadedPra.getBirthdate());
        assertEquals(pra.getGender(), loadedPra.getGender());
        assertEquals(pra.getText(), loadedPra.getText());;

        //die Inhalte könnten eine andere Reihenfolge haben, daher CollectionUtils
        assertTrue(CollectionUtils.isEqualCollection(pra.getAddress(),loadedPra.getAddress()));
        assertTrue(CollectionUtils.isEqualCollection(pra.getIdentifier(),loadedPra.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(pra.getName(),loadedPra.getName()));
        assertTrue(CollectionUtils.isEqualCollection(pra.getTelecom(),loadedPra.getTelecom()));
        assertTrue(CollectionUtils.isEqualCollection(pra.getCommunication(), loadedPra.getCommunication()));
        assertTrue(CollectionUtils.isEqualCollection(pra.getPhoto(), loadedPra.getPhoto()));
        assertTrue(CollectionUtils.isEqualCollection(pra.getQualification(), loadedPra.getQualification()));
    }

    @Test
    @Transactional
    public void testDeleteOnePractitioner(){
        Practitioner pra = returnOnePractitioner();
        Practitioner savedPra = practitionerRepository.save(pra);

        practitionerRepository.deleteById(savedPra.getId());
        assertFalse(practitionerRepository.findById(savedPra.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOnePractitioner(){
        Practitioner savedPra = practitionerRepository.save(new Practitioner());

        Practitioner pra = returnOnePractitioner();
        pra.setId(savedPra.getId());
        practitionerRepository.save(pra);
        Practitioner updatedPra = practitionerRepository.findById(pra.getId()).get();

        assertNotNull(updatedPra.getGender());
        assertNotNull(updatedPra.getText());
        assertNotNull(updatedPra.getBirthdate());
        assertNotNull(updatedPra.getActive());
        assertNotNull(updatedPra.getName());
        assertNotNull(updatedPra.getAddress());
        assertNotNull(updatedPra.getTelecom());
        assertNotNull(updatedPra.getIdentifier());
        assertNotNull(updatedPra.getCommunication());
        assertNotNull(updatedPra.getPhoto());
        assertNotNull(updatedPra.getQualification());
    }

    public static Practitioner returnOnePractitioner() {

        List<Address> addresses = new ArrayList<>();
        List<Identifier> identifiers = new ArrayList<>();
        List<ContractPoint> telecoms = new ArrayList<>();
        List<HumanName> names = new ArrayList<>();
        List<CodeableConcept> communications = new ArrayList<>();
        List<Attachment> photos = new ArrayList<>();
        List<Qualification> qualifications = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();
        List<Coding> comcodings = new ArrayList<>();

        comcodings.add(Coding.builder()
                .system("http://hl7.org/fhir/sid/icd-10")
                .code("G44.1")
                .build());

        codings.add(Coding.builder()
                .display("SS")
                .code("Sozialversicherung")
                .build());

        lines.add("Test Huber");
        lines.add("Pilgramgasse 20");
        lines.add("1050, Wien");

        qualifications.add(Qualification.builder()
                .code(CodeableConcept.builder()
                        .text("general headache")
                        .build())
                .period(new Period(LocalDateTime.of(2005,03,27,00,00),LocalDateTime.of(2033,04,11,00,00)))
                .reference(Reference.builder()
                        .display("HL/, Inc.")
                        .reference("Organization of FHIR")
                        .build())
                .build());

        photos.add(Attachment.builder()
                .contenttype("application/pdf")
                .creation(LocalDateTime.now())
                .data("/9j/4...KAP//Z")
                .language("en")
                .title("Definition of Procedure")
                .build());

        communications.add(CodeableConcept.builder()
                .coding(comcodings)
                .text("en")
                .build());

        names.add(HumanName.builder()
                .prefix(List.of("Mag."))
                .use(HumanName.UseCode.official)
                .text("<div>Test Huber</div>")
                .given(List.of("Test","Angela"))
                .family("Huber")
                .period(new Period(LocalDateTime.of(2010,03,02,00,00), LocalDateTime.now()))
                .build());

        telecoms.add(ContractPoint.builder()
                .value("0699112233")
                .rank(1)
                .system(ContractPoint.SystemCode.phone)
                .use(ContractPoint.UseCode.home)
                .period(new Period(LocalDateTime.of(2006,07,03,00,00),LocalDateTime.of(2050,03,02,00,00)))
                .build());

        identifiers.add(Identifier.builder()
                .period(new Period(LocalDateTime.of(2004,03,26,00,00),LocalDateTime.now()))
                .value("12345334")
                .use(Identifier.UseCode.official)
                .system("urn:ietf:rfc:34563")
                .type(CodeableConcept.builder()
                        .coding(codings)
                        .text("<div></div>")
                        .build())
                .build());

        addresses.add(Address.builder()
                .use(Address.UseCode.home)
                .postalCode("1050")
                .period(new Period(LocalDateTime.of(2020,01,01,00,00), LocalDateTime.now()))
                .line(lines)
                .district("Wien")
                .country("Österreich")
                .city("Wien")
                .type(Address.TypeCode.physical)
                .state("Wien")
                .build());

        Practitioner pra = Practitioner.builder()
                .active(true)
                .address(addresses)
                .birthdate(LocalDate.of(2004,03,26))
                .gender(Practitioner.GenderCode.female)
                .identifier(identifiers)
                .telecom(telecoms)
                .name(names)
                .communication(communications)
                .photo(photos)
                .qualification(qualifications)
                .build();

        Narrative text = new Narrative(Narrative.NarrativeCode.additional, "<div></div>");
        pra.setText(text);

        return pra;
    }
}
