package com.jrl.exercise;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import java.util.ArrayList;

@WebMvcTest(VinController.class)
class VinControllerTest {

    @MockBean
    private VinService vinService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void shouldAllowVINCreation() {

        String testCorrectVin = "0471958692";

        Mockito.when(vinService.createVin(testCorrectVin)).thenReturn(true);

        RestAssuredMockMvc
            .given()
                .body(testCorrectVin)
            .when()
                .post("./")
            .then()
                .statusCode(201)
                .header("Location", Matchers.containsString(String.format("/%s", testCorrectVin)));
    }

    @Test
    void shouldNotAllowVINCreation() {

        String testWrongVin = "0471";

        Mockito.when(vinService.createVin(testWrongVin)).thenReturn(false);

        RestAssuredMockMvc
            .given()
                .body(testWrongVin)
            .when()
                .post("./")
            .then()
                .statusCode(406);
    }

    @Test
    void shouldBeAbleToRetrieveAnyVIN() {

        String testCorrectVin = "0471958692";

        Mockito.when(vinService.getVin(testCorrectVin)).thenReturn(testCorrectVin);

        RestAssuredMockMvc
            .given()
                .body(testCorrectVin)
            .when()
                .get(String.format("./%s", testCorrectVin))
            .then()
                .statusCode(200);
    }

    @Test
    void shouldBeAbleToRetrieveAllVINs() {

        String testCorrectVin = "0471958692";
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(testCorrectVin);

        Mockito.when(vinService.getAllVins()).thenReturn(arrayList);

        RestAssuredMockMvc
            .given()
                .body("vinRepository")
            .when()
                .get("/")
            .then()
                .statusCode(200);
    }
}