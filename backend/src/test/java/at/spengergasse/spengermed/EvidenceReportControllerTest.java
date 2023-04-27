package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.*;
import at.spengergasse.spengermed.repository.EvidenceReportRepository;
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
public class EvidenceReportControllerTest {

    @Autowired
    MockMvc mockMvc; //Ahmt ModuleViewController nach

    @Autowired
    ObjectMapper om; //Macht aus Java-Objekt JSON-String

    @Test
    @Transactional
    public void getAllEvidenceReports() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/evidencereport"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void getAEvidenceReport() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/evidencereport/"+EvidenceReportRepositoryTest.returnOneEvidenceReport().getId().toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void postAEvidenceReport() {
        EvidenceReport evidenceReport = EvidenceReportRepositoryTest.returnOneEvidenceReport();
        String json = null;
        try {
            json = om.writeValueAsString(evidenceReport);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .post("/api/evidencereport/")
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
    public void putAEvidenReport() {
        EvidenceReport evidenceReport = EvidenceReportRepositoryTest.returnOneEvidenceReport();
        evidenceReport.setId("00000000-0000-0000-0000-000000000000");
        String json = null;
        try {
            json = om.writeValueAsString(evidenceReport);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/evidencereport/00000000-0000-0000-0000-000000000000")
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
    public void putANewEvidenceReport() {
        EvidenceReport evidenceReport = EvidenceReportRepositoryTest.returnOneEvidenceReport();
        evidenceReport.setId("12341234");
        String json = null;
        try {
            json = om.writeValueAsString(evidenceReport);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/api/evidencereport/12341234")
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
    public void deleteAEvidenceReport() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/api/evidencereport/"+EvidenceReportRepositoryTest.returnOneEvidenceReport().getId().toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()); //Statuscode: 200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void deleteAEvidenceReportNotFound() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/api/evidenceReport/ihiwvuejf"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isNotFound()); //Statuscode: 404
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
