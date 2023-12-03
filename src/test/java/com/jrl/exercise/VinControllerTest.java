package com.jrl.exercise;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;

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
    public void shouldBeAbleToCreateVin() {
        String vin = "047195869-2";
        Mockito.when(vinService.createVin(vin)).thenReturn(true);

        RestAssuredMockMvc
                .given().body(vin)
                .when().post()
                .then().assertThat().statusCode(201)
                .body(equalTo("true"));
    }

    @Test
    public void shouldNotBeAbleToCreateInvalidVin() {
        String vin = "047195869-6";
        Mockito.when(vinService.createVin(vin)).thenReturn(false);

        RestAssuredMockMvc
                .given().body(vin)
                .when().post()
                .then().assertThat().statusCode(406)
                .body(equalTo("false"));
    }

    @Test
    public void vinShouldBeInRepository() {
        String vin = "047195869-2";
        Mockito.when(vinService.getVinRepository()).thenReturn("Current VINs: ".concat(vin));

        RestAssuredMockMvc
                .given().when().get("/temp/repository")
                .then().assertThat().statusCode(200)
                .body(containsString(vin));
    }

    @Test
    public void vinShouldNotBeInRepository() {
        String vin = "047195869-6";
        Mockito.when(vinService.getVinRepository()).thenReturn("Current VINs: ");

        RestAssuredMockMvc
                .given().when().get("/temp/repository")
                .then().assertThat().statusCode(200)
                .body(not(containsString(vin)));
    }

    @Test
    public void shouldNotBeAbleToGetInvalidVin() {
        RestAssuredMockMvc
                .given().when().get("/temp/4040")
                .then().statusCode(404);
    }

}