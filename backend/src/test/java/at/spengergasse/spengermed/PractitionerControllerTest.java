package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Practitioner;
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

@SpringBootTest
@AutoConfigureMockMvc
public class PractitionerControllerTest {

    @Autowired
    MockMvc mockMvc; //Ahmt ModuleViewController nach

    @Autowired
    ObjectMapper om; //Macht aus Java-Objekt JSON-String

    @Test
    public void getAllPractitioner() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/practitioner"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAPractitioner() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/practitioner/00000000-0000-0000-0001-000000000000"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postAPractitioner() {
        Practitioner practitioner = PractitionerRepositoryTest.returnOnePractitioner();
        String json = null;
        try {
            json = om.writeValueAsString(practitioner);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/practitioner/")
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
    public void putAPractitioner() {
        Practitioner practitioner = PractitionerRepositoryTest.returnOnePractitioner();
        practitioner.setId("00000000-0000-0000-0001-000000000000");
        String json = null;
        try {
            json = om.writeValueAsString(practitioner);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/practitioner/00000000-0000-0000-0001-000000000000")
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
    public void putANewPractitioner() {
        Practitioner practitioner = PractitionerRepositoryTest.returnOnePractitioner();
        practitioner.setId("he38261");
        String json = null;
        try {
            json = om.writeValueAsString(practitioner);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/practitioner/he38261")
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
    public void deleteAPractitioner() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/api/practitioner/00000000-0000-0000-0001-000000000000"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteAPractitionerNotFound() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/api/practitioner/ihiwvuejf"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isNotFound()); //Statuscode: 404
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
