package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Medication;
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

@SpringBootTest
@AutoConfigureMockMvc
public class MedicationControllerTest {

    @Autowired
    MockMvc mockMvc; //Ahmt ModuleViewController nach

    @Autowired
    ObjectMapper om; //Macht aus Java-Objekt JSON-String

    @Test
    @Transactional
    public void getAllMedications() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/medication"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void getAMedications() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/medication/11111000-0000-0000-0000-000000000000"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void postAMedication() {
        Medication medication = MedicationRepositoryTest.returnOneMedication();
        String json = null;
        try {
            json = om.writeValueAsString(medication);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/medication/")
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
    public void putAMedication() {
        Medication medication = MedicationRepositoryTest.returnOneMedication();
        medication.setId("11111000-0000-0000-0000-000000000000");
        String json = null;
        try {
            json = om.writeValueAsString(medication);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/medication/11111000-0000-0000-0000-000000000001")
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
    public void putANewmedication() {
        Medication medication = MedicationRepositoryTest.returnOneMedication();
        medication.setId("12341234");
        String json = null;
        try {
            json = om.writeValueAsString(medication);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/medication/12341234")
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
    public void deleteAmedication() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/api/medication/11111000-0000-0000-0000-000000000001"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
