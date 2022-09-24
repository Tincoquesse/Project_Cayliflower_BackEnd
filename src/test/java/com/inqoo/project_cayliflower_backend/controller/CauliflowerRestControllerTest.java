package com.inqoo.project_cayliflower_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inqoo.project_cayliflower_backend.model.*;
import com.inqoo.project_cayliflower_backend.repository.CategoryRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainerRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainingScheduleRepo;
import com.inqoo.project_cayliflower_backend.service.CauliflowerService;
import com.inqoo.project_cayliflower_backend.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

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

    @Autowired
    private CauliflowerService cauliflowerService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainingScheduleRepo trainingScheduleRepo;

    private void aTrainer(String firstName, String lastName) {
        trainerRepo.save(new Trainer(firstName, lastName, "Bio", new HashSet<>()));
    }

    private void aTraining(String name) {
        String description = "Testowo";
        BigDecimal price = BigDecimal.valueOf(34);
        int duration = 5;

        trainingRepo.save(new Training(name, description, price, duration, new HashSet<>()));
    }

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
        aTrainer("Zdzich", "Mnich");
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
        aTrainer("Zdzich", "Mnich");

        trainingRepo.save(new Training("testTraining",
                "tesDescription",
                new BigDecimal(23),
                24,
                new HashSet<>()));

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO =
                new TrainerToTrainingAssigmentDTO("testTraining", "Zdzich", "Mnich", new HashSet<>());
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
        aTrainer("Zdzich", "Mnich");

        aTraining("TestI");

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO =
                new TrainerToTrainingAssigmentDTO("testII", "Mściwój", "Kubek", new HashSet<>());
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
        aTrainer("Zdzich", "Mnich");

        aTraining("TestI");

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO =
                new TrainerToTrainingAssigmentDTO("TestI", "Franek", "Janek", new HashSet<>());
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
        aTrainer("Zdzich","Mnich");

        aTraining("TestI");

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO =
                new TrainerToTrainingAssigmentDTO("TestI", "Zdzich", "Mnich", new HashSet<>());
        String json = objectMapper.writeValueAsString(trainerToTrainingAssigmentDTO);

        //when
        MvcResult mvcFirstResult = mvcPostAssigmentResults(json);
        MvcResult mvcSecondResult = mvcPostAssigmentResults(json);
        int status = mvcSecondResult.getResponse().getStatus();

        //then
        assertThat(status).isEqualTo(422);
    }
    MvcResult mvcPostAssigmentResults(String json) throws Exception {
        return this.mockMvc.perform(post("/api/assigment")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andReturn();
    }


    @Test
    public void shouldCheckTrainerDayAssigment() throws Exception {
        //GIVEN
        aTrainer("Zdzich","Mnich");
        aTraining("IT", "Java", "Spring");

        Set<Instant> dates = new HashSet<>();
        dates.add(Instant.parse("2023-02-03T10:30:00.00Z"));
        dates.add(Instant.parse("2023-02-10T10:30:00.00Z"));

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO =
                new TrainerToTrainingAssigmentDTO("Spring", "Zdzich", "Mnich", dates);
        String json = objectMapper.writeValueAsString(trainerToTrainingAssigmentDTO);

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(post("/api/assigment")
                        .contentType(APPLICATION_JSON).content(json))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        //THEN
        assertThat(status).isEqualTo(200);

    }

    private void trainerAssignedToTraining(String firstName, String lastName, String trainingName) {
        trainerService.addTrainer(new TrainerDTO(firstName, lastName, "bio"));
        cauliflowerService.assignToTraining(new TrainerToTrainingAssigmentDTO(trainingName,
                firstName, lastName, new HashSet<>()));
    }

    private void aTraining(String categoryName, String subcategoryName, String trainingName) {
        cauliflowerService.addCategory(new CategoryDTO(categoryName, "it_descriotion", new ArrayList<>()));
        cauliflowerService.addSubcategory(new SubcategoryDTO(subcategoryName), categoryName);
        cauliflowerService.addTraining(new TrainingDTO(trainingName,"Spring_description",
                new BigDecimal(210),2, new HashSet<>(), new HashSet<>()), subcategoryName);
    }
}
