package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc //Anfragen an Controller geschickt und Responses ausgewertet
public class PatientControllerTest {

    @Autowired
    MockMvc mockMvc; //Ahmt ModuleViewController nach

    @Autowired
    ObjectMapper om; //Macht aus Java-Objekt JSON-String

    @Test
    @Transactional
    public void getAllPatients() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/patient"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void getAPatients() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/patient/00000000-0000-0000-0000-000000000001"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void postAPatient() {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        String json = null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/patient/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated()); //Statuscode: 201
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void putAPatient() {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        patient.setId("00000000-0000-0000-0000-000000000000");
        String json = null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/patient/00000000-0000-0000-0000-000000000000")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void putANewPatient() {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        patient.setId("12341234");
        String json = null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/patient/12341234")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated()); //Statuscode: 202
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void deleteAPatient() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/api/patient/00000000-0000-0000-0000-000000000000"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void deleteAPatientNotFound() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/api/patient/ihiwvuejf"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isNotFound()); //Statuscode: 404
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void
    postInvalidDeceasedPatient() {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        //patient.setDeceasedDateTime(LocalDateTime.now());
        patient.setDeceasedDateTime(LocalDateTime.now());
        patient.setDeceasedBoolean(false);
        String json = null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/patient/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Validator wird getestet, ob in Narrative (text) beide Attribute nicht null sind
    // soll ein 400 bad Request retourniert werden.
    @Test
    public void putInvalidNarrativePatient() {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        Narrative narrative = new Narrative();
        narrative.setStatus(null);
        narrative.setDiv(null);
        patient.setText(narrative);
        //patient.setActive(null);
        String json = null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/patient")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postAFutureBirthDatePatient() {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        patient.setBirthDate(LocalDate.of(2060, 03, 03));
        String json = null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/patient/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest()); //Statuscode: 400
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Test
    public void postAnInvalidIdPatient() {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        patient.setId("?!!");
        String json = null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/patient/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest()); //Statuscode: 400
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void postAnInvalidSvnrPatient() {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        List<Identifier> identifiers = patient.getIdentifier();
        List<Coding> codingsvnr = new ArrayList<>();

        codingsvnr.add(Coding.builder().code("SS").build());

        Identifier identifier = Identifier.builder()
                .type(CodeableConcept.builder().coding(codingsvnr).build()).value("3049260304").build();

        identifiers.add(identifier);

        patient.setIdentifier(identifiers);

        String json = null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/patient/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest()); //Statuscode: 400
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
