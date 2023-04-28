package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.ImmunizationRepository;
import at.spengergasse.spengermed.repository.RiskAssessmentRepository;
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
public class ImmunizationRepositoryTest {
    @Autowired
    ImmunizationRepository immunizationRepository;

    @Test
    @Transactional
    public void testSaveAndLoadOneImmunization(){

        Immunization i = returnOneImmunization();

        Immunization savedi = immunizationRepository.save(i);
        Immunization loadedi = immunizationRepository.findById(savedi.getId()).get();

        assertNotNull(loadedi);

        assertEquals(i.getExpirationdate(), loadedi.getExpirationdate());
        assertEquals(i.getIotnumber(), loadedi.getIotnumber());
        assertEquals(i.getPatient(), loadedi.getPatient());
        assertEquals(i.getOccurrenceDateTime(), loadedi.getOccurrenceDateTime());
        assertEquals(i.getOccurrenceString(), loadedi.getOccurrenceString());
        assertEquals(i.getText(), loadedi.getText());

    }

    @Test
    @Transactional
    public void testDeleteOneImmunization(){
        Immunization i = returnOneImmunization();
        Immunization savedi = immunizationRepository.save(i);

        immunizationRepository.deleteById(savedi.getId());
        assertFalse(immunizationRepository.findById(savedi.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneImmunization(){
        Immunization savedi = immunizationRepository.save(new Immunization());

        Immunization i = returnOneImmunization();
        i.setId(savedi.getId());
        immunizationRepository.save(i);
        Immunization updatedi = immunizationRepository.findById(i.getId()).get();


        assertNotNull(updatedi.getExpirationdate());
        assertNotNull(i.getIotnumber());
        assertNotNull(i.getPatient());
        assertNotNull(i.getOccurrenceString());
        assertNotNull(i.getOccurrenceDateTime());
    }
    public static Immunization returnOneImmunization() {




        Immunization i = Immunization.builder()
                .expirationdate(LocalDate.of(2010,03,03 ))
                .iotnumber("")
                .patient(Reference.builder()
                        .display("")
                        .identifier(Identifier.builder()
                                .system("")
                                .build())
                        .build())
                .occurrenceString("")
                .occurrenceDateTime(LocalDate.of(2010,05,05 ))
                .build();


        return i;
    }
}
