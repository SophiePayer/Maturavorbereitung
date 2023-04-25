package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.EncounterRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class EncounterRepositoryTest {

    @Autowired
    EncounterRepository encounterRepository;

    @Test
    @Transactional
    public void testSaveAndLoadOneEncounter(){

        Encounter en = returnOneEncounter();

        Encounter savedEn = encounterRepository.save(en);
        Encounter loadedEn = encounterRepository.findById(savedEn.getId()).get();

        assertNotNull(loadedEn);

        assertEquals(en.getPeriod(), loadedEn.getPeriod());
        assertEquals(en.getPartOf(), loadedEn.getPartOf());
        assertEquals(en.getStatus(), loadedEn.getStatus());
        assertEquals(en.getSubject(), loadedEn.getSubject());
        assertEquals(en.getText(), loadedEn.getText());

        assertTrue(CollectionUtils.isEqualCollection(en.getIdentifier(),loadedEn.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(en.getAppointment(), loadedEn.getAppointment()));
        assertTrue(CollectionUtils.isEqualCollection(en.getDiagnosis(), loadedEn.getDiagnosis()));
        assertTrue(CollectionUtils.isEqualCollection(en.getParticipant(), loadedEn.getParticipant()));
        assertTrue(CollectionUtils.isEqualCollection(en.getEpisodeOfCare(), loadedEn.getEpisodeOfCare()));
        assertTrue(CollectionUtils.isEqualCollection(en.getReasonReference(), loadedEn.getReasonReference()));
        assertTrue(CollectionUtils.isEqualCollection(en.getType(), loadedEn.getType()));
    }

    @Test
    @Transactional
    public void testDeleteOneEncounter(){
        Encounter en = returnOneEncounter();
        Encounter savedEn = encounterRepository.save(en);

        encounterRepository.deleteById(savedEn.getId());
        assertFalse(encounterRepository.findById(savedEn.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneEncounter(){
        Encounter savedEn = encounterRepository.save(new Encounter());

        Encounter en = returnOneEncounter();
        en.setId(savedEn.getId());
        encounterRepository.save(en);
        Encounter updatedEn = encounterRepository.findById(en.getId()).get();

        assertNotNull(updatedEn.getText());
        assertNotNull(updatedEn.getAppointment());
        assertNotNull(updatedEn.getSubject());
        assertNotNull(updatedEn.getDiagnosis());
        assertNotNull(updatedEn.getStatus());
        assertNotNull(updatedEn.getPeriod());
        assertNotNull(updatedEn.getPartOf());
        assertNotNull(updatedEn.getIdentifier());
        assertNotNull(updatedEn.getEpisodeOfCare());
        assertNotNull(updatedEn.getParticipant());
        assertNotNull(updatedEn.getReasonReference());
        assertNotNull(updatedEn.getType());
    }

    public static Encounter returnOneEncounter() {

        List<Reference> appointments = new ArrayList<>();
        List<Diagnosis> diagnosis = new ArrayList<>();
        List<Reference> episodesOfCare = new ArrayList<>();
        List<Identifier> identifiers = new ArrayList<>();
        List<Participant> participants = new ArrayList<>();
        List<Reference> reasons = new ArrayList<>();
        List<CodeableConcept> type = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();
        List<Coding> parcodings = new ArrayList<>();
        List<CodeableConcept> codeableconcepts = new ArrayList<>();
        List<Coding> typecodings = new ArrayList<>();
        List<StatusHistory> statushistory = new ArrayList<>();

        statushistory.add(StatusHistory.builder()
                .status(StatusHistory.StatusCode.finished)
                .period(Period.builder().start(LocalDateTime.of(2020,03,03,00,00)).end(LocalDateTime.of(2021,04,04,00,00)).build())
                .build());

        typecodings.add(Coding.builder()
                .system("http://snomed.info/sct")
                .code("39065001")
                .display("Burn of scar")
                .build());

        type.add(CodeableConcept.builder()
                .text("Condition")
                .coding(typecodings)
                .build());

        reasons.add(Reference.builder()
                .type("Condition/con1234")
                .display("Condition")
                .build());

        parcodings.add(Coding.builder()
                .code("CC")
                .build());

        codeableconcepts.add(CodeableConcept.builder()
                .text("Example")
                .coding(parcodings)
                .build());

        participants.add(Participant.builder()
                .type(codeableconcepts)
                .build());

        identifiers.add(Identifier.builder()
                .use(Identifier.UseCode.official)
                .value("123456798")
                .period(Period.builder().start(LocalDateTime.of(2000,04,04,00,00)).end(LocalDateTime.of(2030,04,04,00,00)).build())
                .build());

        episodesOfCare.add(Reference.builder()
                .display("EpisodeOfCare/eoc1234")
                .type("EpisodeOfCare")
                .build());

        codings.add(Coding.builder()
                .code("CC")
                .build());

        diagnosis.add(Diagnosis.builder()
                .rank(1)
                .use(CodeableConcept.builder()
                        .coding(codings)
                        .build())
                .condition(Reference.builder().display("Flu").build())
                .build());

        appointments.add(Reference.builder()
                .type("Appointment")
                .display("Appointment/ap1234")
                .identifier(Identifier.builder()
                        .period(Period.builder().start(LocalDateTime.of(2012,04,04,00,00)).end(LocalDateTime.now()).build())
                        .value("123124425533")
                        .use(Identifier.UseCode.usual)
                        .build())
                .build());

        Reference partOf = Reference.builder()
                .display("Encounter/en123456")
                .identifier(Identifier.builder()
                        .use(Identifier.UseCode.official)
                        .value("123456789")
                        .period(Period.builder().start(LocalDateTime.of(2019,01,01,00,00)).end(LocalDateTime.of(2024,12,31,00,00)).build())
                        .build())
                .type("Encounter")
                .build();

        Reference subject = Reference.builder()
                .identifier(Identifier.builder()
                        .use(Identifier.UseCode.official)
                        .value("9876543212")
                        .period(Period.builder().start(LocalDateTime.of(2000,01,01,00,00)).end(LocalDateTime.of(2030,12,31,00,00)).build())
                        .build())
                .display("Patient/f654")
                .type("Patient")
                .build();

        Encounter en = Encounter.builder()
                .appointment(appointments)
                .diagnosis(diagnosis)
                .episodeOfCare(episodesOfCare)
                .identifier(identifiers)
                .participant(participants)
                .partOf(partOf)
                .period(Period.builder().start(LocalDateTime.of(2020,04,27,00,00)).end(LocalDateTime.of(2020,04,28,00,00)).build())
                .reasonReference(reasons)
                .status(Encounter.StatusCode.finished)
                .statusHistory(statushistory)
                .subject(subject)
                .type(type)
                .build();

        Narrative text = new Narrative(Narrative.NarrativeCode.additional, "<div></div>");
        en.setText(text);

        return en;
    }
}
