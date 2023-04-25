package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.MeasureRepository;
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
public class MeasureRepositoryTest {

    @Autowired
    MeasureRepository measureRepository;

    @Test
    @Transactional
    public void testSaveAndLoadOneMeasure(){

        Measure me = returnOneMeasure();

        Measure savedCMe = measureRepository.save(me);
        Measure loadedMe = measureRepository.findById(savedCMe.getId()).get();

        assertNotNull(loadedMe);

        assertEquals(me.getUrl(), loadedMe.getUrl());
        assertEquals(me.getDate(), loadedMe.getDate());
        assertEquals(me.getVersion(), loadedMe.getVersion());
        assertEquals(me.getSubjectCodeableConcept(), loadedMe.getSubjectCodeableConcept());
        assertEquals(me.getSubjectReference(), loadedMe.getSubjectReference());
        assertEquals(me.getGroup(), loadedMe.getGroup());
        assertTrue(CollectionUtils.isEqualCollection(me.getIdentifier(), loadedMe.getIdentifier()));

    }

    @Test
    @Transactional
    public void testDeleteOneMeasure(){
        Measure me = returnOneMeasure();
        Measure savedMe = measureRepository.save(me);

        measureRepository.deleteById(savedMe.getId());
        assertFalse(measureRepository.findById(savedMe.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testUpdateOneMeasure(){
        Measure savedMe = measureRepository.save(new Measure());

        Measure me = returnOneMeasure();
        me.setId(savedMe.getId());
        measureRepository.save(me);
        Measure updatedMe = measureRepository.findById(me.getId()).get();

        assertNotNull(updatedMe.getText());
        assertNotNull(updatedMe.getSubjectReference());
        assertNotNull(updatedMe.getDate());
        assertNotNull(updatedMe.getUrl());
        assertNotNull(updatedMe.getSubjectCodeableConcept());
        assertNotNull(updatedMe.getVersion());
        assertNotNull(updatedMe.getIdentifier());
        assertNotNull(updatedMe.getGroup());
    }

    public static Measure returnOneMeasure() {

        List<Coding> codings = new ArrayList<>();
        List<Identifier> identifiers = new ArrayList<>();
        List<Coding> groupcodings = new ArrayList<>();

        identifiers.add(Identifier.builder()
                .value("12345")
                .build());

        codings.add(Coding.builder()
                .system("http://terminology.hl7.org/CodeSystem/Measure-category")
                .code("problem-list-item")
                .display("Problem List Item")
                .build());

        groupcodings.add(Coding.builder()
                .system("http://terminology.hl7.org/CodeSystem/")
                .code("item")
                .display("Item")
                .build());

        Measure me = Measure.builder()
                .url("https://")
                .date(LocalDateTime.of(2020,02,23,0,0,0))
                .subjectReference(Reference.builder()
                        .reference("reference")
                        .identifier(Identifier.builder()
                                .value("12345")
                                .build())
                        .type("subject")
                        .display("Subject")
                        .build())
                .version("V3")
                .subjectCodeableConcept(CodeableConcept.builder()
                        .coding(codings)
                        .text("Code")
                        .build())
                .identifier(identifiers)
                .group(Group.builder()
                        .description("Description")
                        .code(CodeableConcept.builder()
                                .text("test")
                                .coding(groupcodings)
                                .build())
                        .build())
                .build();

        Narrative text = new Narrative(Narrative.NarrativeCode.additional, "<div></div>");
        me.setText(text);

        return me;
    }
}
