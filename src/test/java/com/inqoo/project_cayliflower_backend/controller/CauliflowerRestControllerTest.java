package com.inqoo.project_cayliflower_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inqoo.project_cayliflower_backend.model.*;
import com.inqoo.project_cayliflower_backend.repository.CategoryRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainerRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CauliflowerRestControllerTest {

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    TrainingRepo trainingRepo;
    @Autowired
    TrainerRepo trainerRepo;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAllCategories() throws Exception {
        //GIVEN
        categoryRepo.save(new Category("IT", "IT desc", Collections.emptyList()));
        categoryRepo.save(new Category("Sales", "Sales desc", Collections.emptyList()));

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(get("/api/categories")).andReturn();

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
        MvcResult mvcResult = this.mockMvc.perform(get("/api/categories")).andReturn();

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

        trainerRepo.save(new Trainer(firstName, lastName, bio, new HashSet<>()));
        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/api/trainers")).andReturn();

        //then
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        List<TrainerDTO> trainers = Arrays.asList(objectMapper.readValue(contentAsString, TrainerDTO[].class));

    }

    @Test
    void shouldAddTrainerToTraining() throws Exception {
        //given
        String firstName = "Zdich";
        String lastName = "Mnich";
        String bio = "test";

        trainerRepo.save(new Trainer(firstName, lastName, bio, new HashSet<>()));

        trainingRepo.save(new Training("testTraining",
                "tesDescription",
                new BigDecimal(23),
                24,
                new HashSet<>()));

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO =
                new TrainerToTrainingAssigmentDTO("testTraining", firstName, lastName);
        String json = objectMapper.writeValueAsString(trainerToTrainingAssigmentDTO);

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/api/assigment")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        //then
        assertThat(status).isEqualTo(200);
        assertThat(trainingRepo.findByName("testTraining").get().getTrainers().size()).isEqualTo(1);

    }

    @Test
    public void shouldReturnBadRequestWhenTrainingIsNotExist() throws Exception {
        //given
        String firstName = "Mściwój";
        String lastName = "Kubek";
        String bio = "test";

        trainerRepo.save(new Trainer(firstName, lastName, bio, new HashSet<>()));

        String name = "TestI";
        String description = "Testowo";
        BigDecimal price = BigDecimal.valueOf(34);
        int duration = 5;

        trainingRepo.save(new Training(name, description, price, duration, new HashSet<>()));

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO =
                new TrainerToTrainingAssigmentDTO("testII", firstName, lastName);
        String json = objectMapper.writeValueAsString(trainerToTrainingAssigmentDTO);

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/api/assigment")
                        .contentType(APPLICATION_JSON).content(json))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        //then
        assertThat(status).isEqualTo(404);

    }

    @Test
    public void shouldReturnBadRequestWhenTrainerIsNotExist() throws Exception {
        //given
        String firstName = "Mściwój";
        String lastName = "Kubek";
        String bio = "test";

        trainerRepo.save(new Trainer(firstName, lastName, bio, new HashSet<>()));

        String name = "TestI";
        String description = "Testowo";
        BigDecimal price = BigDecimal.valueOf(34);
        int duration = 5;

        trainingRepo.save(new Training(name, description, price, duration, new HashSet<>()));

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO =
                new TrainerToTrainingAssigmentDTO(name, "Franek", lastName);
        String json = objectMapper.writeValueAsString(trainerToTrainingAssigmentDTO);

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/api/assigment")
                        .contentType(APPLICATION_JSON).content(json))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        //then
        assertThat(status).isEqualTo(404);

    }

    @Test
    public void shouldReturnNameAlreadyTakenWhenTrainerIsTheSame() throws Exception {
        //given
        String firstName = "Zdich";
        String lastName = "Mnich";
        String bio = "test";

        trainerRepo.save(new Trainer(firstName, lastName, bio, new HashSet<>()));

        String trainingNAme = "testTraining";
        trainingRepo.save(new Training(trainingNAme,
                "tesDescription",
                new BigDecimal(23),
                24,
                new HashSet<>()));

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO =
                new TrainerToTrainingAssigmentDTO(trainingNAme, firstName, lastName);
        String json = objectMapper.writeValueAsString(trainerToTrainingAssigmentDTO);


        this.mockMvc.perform(post("/api/assigment")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andReturn();
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/api/assigment")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        //then
        assertThat(status).isEqualTo(422);
    }
}
