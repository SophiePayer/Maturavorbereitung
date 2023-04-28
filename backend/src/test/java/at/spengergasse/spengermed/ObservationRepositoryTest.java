package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.ObservationRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Ref;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ObservationRepositoryTest {

    @Autowired
    ObservationRepository observationRepository;

    @Test
    @Transactional
    public void testSaveAndLoadOneObservation(){

        Observation obs = returnOneObservation();

        Observation savedObs = observationRepository.save(obs);
        Observation loadedObs = observationRepository.findById(savedObs.getId()).get();

        assertNotNull(loadedObs);

        assertEquals(obs.getObservationDefinition(), loadedObs.getObservationDefinition());
        assertEquals(obs.getCanonical(), loadedObs.getCanonical());
        assertEquals(obs.getInstantiatesReference(), loadedObs.getInstantiatesReference());
        assertEquals(obs.getBasedon(), loadedObs.getBasedon());
        assertEquals(obs.getTriggeredby(), loadedObs.getTriggeredby());
        assertEquals(obs.getIdentifier(), loadedObs.getIdentifier());

    }


    @Test
    @Transactional
    public void testDeleteOneObservation(){
        Observation obs = returnOneObservation();
        Observation savedObs = observationRepository.save(obs);

        observationRepository.deleteById(savedObs.getId());
        assertFalse(observationRepository.findById(savedObs.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneObservation(){
        Observation savedObs = observationRepository.save(new Observation());

        Observation obs = returnOneObservation();
        obs.setId(savedObs.getId());
        observationRepository.save(obs);
        Observation updatedObs = observationRepository.findById(obs.getId()).get();


    }

    public static Observation returnOneObservation() {

        List<Identifier> identifiers = new ArrayList<>();
        List<Reference> basedon = new ArrayList<>();
        List<TriggeredBy> triggeredby = new ArrayList<>();

        identifiers.add(Identifier.builder()
                .value("12345")
                .build());

        basedon.add(Reference.builder()
                .reference("MedicationRequest")
                .type("Proposal")
                .build());

        triggeredby.add(TriggeredBy.builder()
                        .observation(Reference.builder()
                                .display("12345")
                                .reference("Observation of FHIR")
                                .build())
                .build());

        Observation obs = Observation.builder()
                .instantiatesReference(Reference.builder()
                        .reference("observationdef")
                        .identifier(Identifier.builder()
                                .value("12345")
                                .build())
                        .build())
                .identifier(identifiers)
                .canonical("observationdef")
                .build();



        return obs;
    }
}
