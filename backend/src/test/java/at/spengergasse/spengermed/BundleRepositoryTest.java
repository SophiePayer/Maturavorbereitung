package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.BundleRepository;
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
public class BundleRepositoryTest {

    @Autowired //Dependency Injection --> nur eine Instanz des BundleRepository und wird eingefügt
    private BundleRepository bundleRepository;

    @Autowired
    ObjectMapper om;

    @Test
    @Transactional
    public void testSaveAndLoadOneBundle(){

        //1. Bundle Objekt
        Bundle b = returnOneBundle();

        //1.1. Bundleen in die db speichern
        Bundle savedB = bundleRepository.save(b);

        //1.2. Bundle aus der db laden
        Bundle loadedB = bundleRepository.findById(savedB.getId()).get();

        //Check ob nach speichern und laden p gleich ist wie loadedP
        assertNotNull(loadedB);

        //assertEquals(b, loadedB); können wir nicht machen, da nicht überprüft wird ob die Inhalte gleich sind (nur Struktur)
        assertEquals(b.getTotal(), loadedB.getTotal());
        assertEquals(b.getIdentifer(), loadedB.getIdentifer());

        //die Inhalte könnten eine andere Reihenfolge haben, daher CollectionUtils
        assertTrue(CollectionUtils.isEqualCollection(b.getLinks(),loadedB.getLinks()));

    }

    @Test
    @Transactional
    public void testDeleteOneBundle(){
        Bundle b = returnOneBundle();
        Bundle savedB = bundleRepository.save(b);

        bundleRepository.deleteById(savedB.getId());
        assertFalse(bundleRepository.findById(savedB.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneBundle(){
        Bundle savedB = bundleRepository.save(new Bundle());

        Bundle b = returnOneBundle();
        b.setId(savedB.getId());
        bundleRepository.save(b);
        Bundle updatedB = bundleRepository.findById(b.getId()).get();

        // Für die Collections -> detached Entity da JPA mit dem Datensatz verbindet

        assertNotNull(updatedB.getIdentifer());
        assertNotNull(updatedB.getTotal());
        assertNotNull(updatedB.getLinks());
    }


    public static Bundle returnOneBundle() { //Alternative zu "new Bundle" bei der man die Attribute beliebig wählen kann

        List<Coding> svnrCodings = new ArrayList<>();
        ArrayList<Link> link = new ArrayList<>();

        link.add(Link.builder()
                .relation("self")
                .uri("https://r4.smarthealthit.org/Patient/?_format=json")
                .build());

        svnrCodings.add(Coding.builder()
                .code("SS")
                .display("Sozialversicherungsnummer")
                .build());

        Bundle b = Bundle.builder()
                .identifer(Identifier.builder()
                        .period(new Period(LocalDateTime.of(2004,03,26,00,00), LocalDateTime.now()))
                        .use(Identifier.UseCode.official)
                        .type(CodeableConcept.builder().coding(svnrCodings).text("<div></div>").build())
                        .system("urn:ietf:rfc:3986")
                        .value("3945200304")
                        .build())
                .links(link)
                .total(10)
                .build();

        return b;
    }
}
