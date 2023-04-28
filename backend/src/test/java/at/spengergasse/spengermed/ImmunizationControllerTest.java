package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Immunization;
import at.spengergasse.spengermed.model.RiskAssessment;
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

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class ImmunizationControllerTest {

    @Autowired
    MockMvc mockMvc; //Ahmt ModuleViewController nach

    @Autowired
    ObjectMapper om; //Macht aus Java-Objekt JSON-String

    @Test
    public void getAllImmunizations() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/immunization"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAImmunization() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/immunization/99990000-0000-0000-0000-000000000000"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Transactional
    @Test
    public void postAImmunization() {
        Immunization immunization = ImmunizationRepositoryTest.returnOneImmunization();
        String json = null;
        try {
            json = om.writeValueAsString(immunization);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/immunization/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated()); //Statuscode: 201
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Transactional
    @Test
    public void putAImmunization() {
        Immunization immunization = ImmunizationRepositoryTest.returnOneImmunization();
        immunization.setId("99990000-0000-0000-0000-000000000000");
        String json = null;
        try {
            json = om.writeValueAsString(immunization);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/immunization/99990000-0000-0000-0000-000000000000")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Transactional
    @Test
    public void putANewImmunization() {
        Immunization immunization = ImmunizationRepositoryTest.returnOneImmunization();
        immunization.setId("99990000-0000-0000-0000-000000000000");
        String json = null;
        try {
            json = om.writeValueAsString(immunization);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/immunization/99800000-0000-0000-0000-000000000000")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated()); //Statuscode: 202
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Test
    public void deleteAImmunization() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/api/immunization/99990000-0000-0000-0000-000000000000"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteAImmunizationNotFound() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/api/immunization/dfdf"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isNotFound()); //Statuscode: 404
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
