package com.inqoo.project_cayliflower_backend.controller;

import static org.assertj.core.api.Assertions.in;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inqoo.project_cayliflower_backend.model.*;
import com.inqoo.project_cayliflower_backend.repository.CategoryRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainerRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CauliflowerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    TrainingRepo trainingRepo;

    @Autowired
    TrainerRepo trainerRepo;

    @Test
    public void shouldGetAllCategories() throws Exception {
        //GIVEN
        categoryRepo.save(new Category("IT", "IT desc", Collections.emptyList()));
        categoryRepo.save(new Category("Sales", "Sales desc", Collections.emptyList()));

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(get("/api/category/all")).andReturn();

        //THEN
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        List<CategoryDTO> categories = Arrays.asList(objectMapper.readValue(contentAsString, CategoryDTO[].class));

        assertThat(categories.size()).isEqualTo(2);
    }

    @Test
    public void shouldGetTheSameNames() throws Exception {
        //GIVEN
        String firstName = "IT";
        String secondName = "Sales";

        categoryRepo.save(new Category(firstName, "IT desc", Collections.emptyList()));
        categoryRepo.save(new Category(secondName, "Sales desc", Collections.emptyList()));

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(get("/api/category/all")).andReturn();

        //THEN
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        List<CategoryDTO> categories = Arrays.asList(objectMapper.readValue(contentAsString, CategoryDTO[].class));


        assertThat(categories).containsExactlyInAnyOrder(new CategoryDTO(firstName, "IT desc", Collections.emptyList()),
                new CategoryDTO(secondName, "Sales desc", Collections.emptyList()));
    }

    @Test
    public void shouldAddTrainer() throws Exception {

        //given
        TrainerDTO trainerDTO = new TrainerDTO("Mariusz", "Wariusz", "test");
        String json = objectMapper.writeValueAsString(trainerDTO);

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/api/trainer")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        //then
        assertThat(status).isEqualTo(201);
        assertThat(trainerRepo.findByFirstNameAndLastName("Mariusz", "Wariusz").get()).isNotNull();

    }
    @Test
    public void shouldGetTrainer() throws Exception {
        //given
        String firstName = "Zdich";
        String lastName = "Mnich";
        String bio = "test";

        trainerRepo.save(new Trainer(firstName, lastName, bio, Collections.emptyList()));
        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/api/trainer")).andReturn();

        //then
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        List<TrainerDTO> trainers = Arrays.asList(objectMapper.readValue(contentAsString, TrainerDTO[].class));

    }
//    @Test
//    public void shouldAddTrainerToTraining(){
//        //given
//        trainingRepo.save(new Training("Basics", "test", new BigDecimal(50), 3));
//        trainerRepo.save(new Trainer("Zbyszek", "Jabłko", "test", Collections.emptyList()));
//
//        //when
//
//        //then
//
//    }

}