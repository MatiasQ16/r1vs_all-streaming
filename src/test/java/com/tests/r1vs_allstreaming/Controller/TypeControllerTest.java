package com.tests.r1vs_allstreaming.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tests.r1vs_allstreaming.Controllers.TypeController;
import com.tests.r1vs_allstreaming.Models.Type;
import com.tests.r1vs_allstreaming.Services.TypeService;
import com.tests.r1vs_allstreaming.Services.TypeServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.will;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class TypeControllerTest {

    @Mock
    private TypeServiceImpl typeService;

    private MockMvc mockMvc;

    @InjectMocks
    private TypeController typeController;

    private JacksonTester<List<Type>> jsonTypeList;
    private JacksonTester<Type> jsonType;

    @BeforeEach
    void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(typeController).build();
    }

    @Test
    public void ufCallCreateTypeReturnCreatedStatus() throws Exception {
        // Given
        Type type = new Type("Streaming");

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/type")
                        .characterEncoding("utf-8")
                        .content(new ObjectMapper().writeValueAsString(type))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                // Then
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void ifCallGetTypesAndExistTypesReturnIt() throws Exception {
        // Given
        List<Type> typeList = new ArrayList<>();
        typeList.add(new Type("Streaming1"));
        typeList.add(new Type("Streaming2"));

        given(this.typeService.findAll()).willReturn(typeList);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonTypeList.write(typeList).getJson());


    }
}
