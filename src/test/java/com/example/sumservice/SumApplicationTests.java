package com.example.sumservice;

import com.example.sumservice.exception.SumNotFoundException;
import com.example.sumservice.model.SumModel;
import com.example.sumservice.repository.SumRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SumApplicationTests {

    @SpringBootTest
    @AutoConfigureMockMvc
    public class PersonControllerMockMvcIntegrationTest {
        @Autowired
        private ResponseModel responseModel;
        @Autowired
        private SumRepo repository;
        @Autowired
        private MockMvc mockMvc;

        @AfterEach
        public void resetDb() {
            repository.deleteAll();
        }

        @Test
        public void givenPerson_whenAdd_thenStatus201andPersonReturned() throws Exception {
            SumModel name = new SumModel("Michail");
            mockMvc.perform(
                    post("/add")
                            .content(responseModel.writeValueAsString(name))
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.name").value("Michail"));
        }
    }

    @Test
    public void givenId_whenGetNotExistingPerson_thenStatus404anExceptionThrown() throws Exception {
        MockMvc mockMvc = null;
        mockMvc.perform(
                post("/persons/Michail"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(SumNotFoundException.class));
    }
}


