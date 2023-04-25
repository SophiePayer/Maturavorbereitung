package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class PatientRepositoryTest {

    @Autowired //Dependency Injection --> nur eine Instanz des PatientRepository und wird eingefügt
    private PatientRepository patientRepository;

    @Autowired
    ObjectMapper om;

    @Test
    @Transactional
    public void testSaveAndLoadOnePatient(){

        //1. Patient Objekt
        Patient p = returnOnePatient();

        //1.1. Patienten in die db speichern
        Patient savedP = patientRepository.save(p);

        //1.2. Patient aus der db laden
        Patient loadedP = patientRepository.findById(savedP.getId()).get();

        //Check ob nach speichern und laden p gleich ist wie loadedP
        assertNotNull(loadedP);

        //assertEquals(p,loadedP); können wir nicht machen, da nicht überprüft wird ob die Inhalte gleich sind (nur Struktur)
        assertEquals(p.getActive(), loadedP.getActive());
        assertEquals(p.getDeceasedBoolean(), loadedP.getDeceasedBoolean());
        assertEquals(p.getBirthDate(), loadedP.getBirthDate());
        assertEquals(p.getGender(), loadedP.getGender());
        assertEquals(p.getText(), loadedP.getText());

        //die Inhalte könnten eine andere Reihenfolge haben, daher CollectionUtils
        assertTrue(CollectionUtils.isEqualCollection(p.getAddress(),loadedP.getAddress()));
        assertTrue(CollectionUtils.isEqualCollection(p.getIdentifier(),loadedP.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(p.getName(),loadedP.getName()));
        assertTrue(CollectionUtils.isEqualCollection(p.getTelecom(),loadedP.getTelecom()));
        assertTrue(CollectionUtils.isEqualCollection(p.getPhoto(),loadedP.getPhoto()));
        assertTrue(CollectionUtils.isEqualCollection(p.getGeneralPractitioner(), loadedP.getGeneralPractitioner()));

    }

    @Test
    @Transactional
    public void testDeleteOnePatient(){
        Patient p = returnOnePatient();
        Patient savedP = patientRepository.save(p);

        patientRepository.deleteById(savedP.getId());
        assertFalse(patientRepository.findById(savedP.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOnePatient(){
       Patient savedP = patientRepository.save(new Patient());

       Patient p = returnOnePatient();
       p.setId(savedP.getId());
       patientRepository.save(p);
       Patient updatedP = patientRepository.findById(p.getId()).get();

        // Für die Collections -> detached Entity da JPA mit dem Datensatz verbindet

        assertNotNull(updatedP.getGender());
        assertNotNull(updatedP.getDeceasedBoolean());
        assertNotNull(updatedP.getText());
        assertNotNull(updatedP.getBirthDate());
        assertNotNull(updatedP.getActive());
        assertNotNull(updatedP.getName());
        assertNotNull(updatedP.getAddress());
        assertNotNull(updatedP.getTelecom());
        assertNotNull(updatedP.getIdentifier());
        assertNotNull(updatedP.getPhoto());
        assertNotNull(updatedP.getGeneralPractitioner());
    }


    public static Patient returnOnePatient() { //Alternative zu "new Patient" bei der man die Attribute beliebig wählen kann
        List<Address> addresses = new ArrayList<>();
        LocalDate birthDate = LocalDate.of(2004,03,26);
        List<HumanName> names = new ArrayList<>();
        List<Identifier> identifiers = new ArrayList<>();
        List<ContractPoint> telecoms = new ArrayList<>();
        List<Attachment> photos = new ArrayList<>();
        List<Reference> references = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        lines.add("Sophie Payer");
        lines.add("Spengergasse 20");
        lines.add("1050 Wien");
        List<Coding> svnrCodings = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();

        codings.add(Coding.builder()
                .code("SS")
                .display("Sozialversicherungsnummer")
                .build());

        references.add(Reference.builder()
                .reference("Practitioner/f005")
                .display("Practitioner")
                .identifier(Identifier.builder()
                        .value("1234567890")
                        .type(CodeableConcept.builder().text("<div></div>").coding(codings).build())
                        .use(Identifier.UseCode.official)
                        .system("urn:ief:ref:1241")
                        .build())
                .build());

        photos.add(Attachment.builder()
                .title("Picture of Patient")
                .language("en")
                .creation(LocalDateTime.now())
                .contenttype("image/jpg")
                .data("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")
                .size(270)
                .build());


        svnrCodings.add(Coding.builder()
                .code("SS")
                .display("Sozialversicherungsnummer")
                .build());

        identifiers.add(Identifier.builder()
                .period(new Period(LocalDateTime.of(2004,03,26,00,00), LocalDateTime.now()))
                .use(Identifier.UseCode.official)
                .type(CodeableConcept.builder().coding(svnrCodings).text("<div></div>").build())
                .system("urn:ietf:rfc:3986")
                .value("3945200304")
                .build());

        names.add(HumanName.builder()
                .period(new Period(LocalDateTime.of(2004,03,26,00,00), LocalDateTime.now()))
                .family("Payer")
                .given(List.of("Sophie"))
                .prefix(List.of("Ing."))
                .text("<div>Sophie Payer</div>")
                .use(HumanName.UseCode.official)
                .suffix(List.of("bakk.tech."))
                .build());

        telecoms.add(ContractPoint.builder()
                .period(new Period(LocalDateTime.of(2022,01,01,00,00), LocalDateTime.of(2022, 04,05,00,00)))
                .use(ContractPoint.UseCode.work)
                .rank(1)
                .system(ContractPoint.SystemCode.email)
                .value("PAY19658@spengergasse.at")
                .build());

        telecoms.add(ContractPoint.builder()
                .period(new Period(LocalDateTime.of(2022,01,01,00,00), LocalDateTime.of(2022, 04,05,00,00)))
                .use(ContractPoint.UseCode.work)
                .rank(1)
                .system(ContractPoint.SystemCode.phone)
                .value("0146578232")
                .build());

        addresses.add(Address.builder()
                .city("Wien")
                .country("Österreich")
                .district("1050")
                .line(lines)
                .period(new Period( LocalDateTime.now(), LocalDateTime.of(2022, 10,03, 00, 00)))
                .type(Address.TypeCode.postal)
                .use(Address.UseCode.home)
                .build());

        Patient p = Patient.builder()
                .active(true)
                .deceasedBoolean(true)
                .address(addresses)
                .birthDate(birthDate)
                .gender(Patient.GenderCode.female)
                .name(names)
                .identifier(identifiers)
                .telecom(telecoms)
                .photo(photos)
                .generalPractitioner(references)
                .build();

        Narrative text = new Narrative(Narrative.NarrativeCode.additional, "<div></div>");
        p.setText(text);

         return p;
    }
}
