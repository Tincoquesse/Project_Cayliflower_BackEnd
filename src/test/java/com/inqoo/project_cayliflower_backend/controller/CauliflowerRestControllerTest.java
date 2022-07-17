package com.inqoo.project_cayliflower_backend.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inqoo.project_cayliflower_backend.model.Category;
import com.inqoo.project_cayliflower_backend.model.CategoryDTO;
import com.inqoo.project_cayliflower_backend.repository.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
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
    CategoryRepo repo;

    @Test
    public void shouldGetAllCategories() throws Exception {

        repo.save(new Category("IT", "IT desc", Collections.emptyList()));
        repo.save(new Category("Sales", "Sales desc", Collections.emptyList()));

        MvcResult mvcResult = this.mockMvc.perform(get("/api/category/all")).andReturn();


        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        List<CategoryDTO> categories = Arrays.asList(objectMapper.readValue(contentAsString, CategoryDTO[].class));

        assertThat(categories.size()).isEqualTo(2);
    }

}