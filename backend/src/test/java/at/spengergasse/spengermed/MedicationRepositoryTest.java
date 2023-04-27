package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.MedicationRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MedicationRepositoryTest {


    @Autowired //Dependency Injection --> nur eine Instanz des medicationRepository und wird eingefügt
    private MedicationRepository medicationRepository;

    @Autowired
    ObjectMapper om;

    @Test
    @Transactional
    public void testSaveAndLoadOneMedication(){

        //1. medication Objekt
        Medication m = returnOneMedication();

        //1.1. medicationen in die db speichern
        Medication savedM = medicationRepository.save(m);

        //1.2. medication aus der db laden
        Medication loadedM = medicationRepository.findById(savedM.getId()).get();

        //Check ob nach speichern und laden p gleich ist wie loadedP
        assertNotNull(loadedM);

        //assertEquals(p,loadedP); können wir nicht machen, da nicht überprüft wird ob die Inhalte gleich sind (nur Struktur)
        assertEquals(m.getStatus(),loadedM.getStatus());
        assertEquals(m.getIngredient(), loadedM.getIngredient());
        assertEquals(m.getCode(), loadedM.getCode());
        assertEquals(m.getBatch(), loadedM.getBatch());
        assertEquals(m.getTotalVolume(), loadedM.getTotalVolume());

        //die Inhalte könnten eine andere Reihenfolge haben, daher CollectionUtils

        assertTrue(CollectionUtils.isEqualCollection(m.getIdentifier(),loadedM.getIdentifier()));


    }

    @Test
    @Transactional
    public void testDeleteOneMedication(){
        Medication m = returnOneMedication();
        Medication savedM = medicationRepository.save(m);

        medicationRepository.deleteById(savedM.getId());
        assertFalse(medicationRepository.findById(savedM.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneMedication(){
        Medication savedM = medicationRepository.save(new Medication());

        Medication m = returnOneMedication();
        m.setId(savedM.getId());
        medicationRepository.save(m);
        Medication updatedM = medicationRepository.findById(m.getId()).get();

        // Für die Collections -> detached Entity da JPA mit dem Datensatz verbindet

        assertNotNull(updatedM.getTotalVolume());
        assertNotNull(updatedM.getCode());
        assertNotNull(updatedM.getStatus());
        assertNotNull(updatedM.getIdentifier());
        assertNotNull(updatedM.getIngredient());
        assertNotNull(updatedM.getBatch());

    }


    public static Medication returnOneMedication() { //Alternative zu "new medication" bei der man die Attribute beliebig wählen kann

        List<Identifier> identifiers = new ArrayList<>();
        List<Coding> svnrCodings = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(Ingredient.builder()
                        .isActive(true)
                        .item(CodeableReference.builder()
                                .concept(CodeableConcept.builder()
                                        .text("")
                                        .build())
                                .reference(Reference.builder()
                                        .type("")
                                        .reference("")
                                        .build())
                                .build())
                        .strengthCC(CodeableConcept.builder()
                                .text("")
                                .build())
                        .strengthQuantity(Quantity.builder()
                                .decimal(null)
                                .comparator("")
                                .unit("")
                                .code("")
                                .system("")
                                .build())
                        .strengthRatio(Ratio.builder()
                                .numerator(Quantity.builder()
                                        .decimal(null)
                                        .comparator("")
                                        .unit("")
                                        .code("")
                                        .system("")
                                        .build())
                                .build())
                .build());
        codings.add(Coding.builder()
                .code("SS")
                .display("Sozialversicherungsnummer")
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



        Medication m = Medication.builder()
                .identifier(identifiers)
                .batch(Batch.builder()
                        .iotNumber("123")
                        .expirationDate(LocalDateTime.of(2004,03,26,00,00))
                        .build())
                .status(Medication.StatusCode.active)
                .totalVolume(Quantity.builder()
                        .decimal(null)
                        .comparator("")
                        .unit("")
                        .code("")
                        .system("")
                        .build())
                .ingredient(ingredients)
                .code(CodeableConcept.builder()
                        .text("")
                        .coding(codings)
                        .build())
                .build();

        Narrative text = new Narrative(Narrative.NarrativeCode.additional, "<div></div>");
        m.setText(text);

        return m;
    }
}
