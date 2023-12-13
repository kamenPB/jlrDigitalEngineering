package com.jrl.exercise;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeTests {

    private static final String EMPLOYEES_URL = "/employees/";
    private static final String SPECIFIC_DEPARTMENT_URL = "/employees/IT";
    private static final String DISTINCT_DEPARTMENTS_URL = "/departments/";
    private static final String TOP_EARNERS_URL = "/departments/top/";
    private static final String EMPLOYEE_COUNTER_URL = "/departments/count/";

    @Autowired
    private TestRestTemplate template;

    @Test
    void getEmployees_basicTest() throws JSONException {
        HttpHeaders headers = createHttpContentTypeHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = template.exchange(EMPLOYEES_URL, HttpMethod.GET, httpEntity, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());

        String expectedResponse = "[ { 'id': 1 }, { 'id': 2 }, { 'id': 3 }, { 'id': 4 } ]"; // ORDER should not matter (json path expressions)
        assertEquals("application/json", response.getHeaders().get("Content-Type").get(0));
        JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
    }

    @Test
    void getEmployeesByDepartment_basicTest() throws JSONException {
        HttpHeaders headers = createHttpContentTypeHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = template.exchange(SPECIFIC_DEPARTMENT_URL, HttpMethod.GET, httpEntity, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());

        String expectedResponse = "[ { 'id': 1 }, { 'id': 2 } ]";
        assertEquals("application/json", response.getHeaders().get("Content-Type").get(0));
        JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
    }


    // try json map in request
    @Test
    void addNewEmployee_SuccessTest() throws JSONException {
        String requestBody = "{" +
                "    \"id\": 5," +
                "    \"name\": \"E\"," +
                "    \"salary\": 25000," +
                "    \"department\": {" +
                "    \"id\": 2," +
                "    \"departmentName\": \"Finance\"" +
                "    }" +
                "}";
        HttpHeaders headers = createHttpContentTypeHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
        ResponseEntity<String> response = template.exchange(EMPLOYEES_URL, HttpMethod.POST, httpEntity, String.class);
//        System.out.println(response.getHeaders());
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("text/plain;charset=UTF-8", response.getHeaders().get("Content-Type").get(0));

        template.exchange(EMPLOYEES_URL, HttpMethod.DELETE, httpEntity, String.class);

//        String expectedResponse = "Employee added successfully";
//        JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
    }

    @Test
    void getDistinctDepartmentNames_basicTest() throws JSONException {
        HttpHeaders headers = createHttpContentTypeHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = template.exchange(DISTINCT_DEPARTMENTS_URL, HttpMethod.GET, httpEntity, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());

        String expectedResponse = "[ { 'id': 1 }, { 'id': 2 }, { 'id': 3 } ]";
        assertEquals("application/json", response.getHeaders().get("Content-Type").get(0));
        JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
    }

    @Test
    void getTopEarnersInEachDepartment_basicTest_OneTopEarnerPerDepartment() throws JSONException {
        HttpHeaders headers = createHttpContentTypeHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = template.exchange(TOP_EARNERS_URL, HttpMethod.GET, httpEntity, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());

        String expectedResponse = "[ { 'id': 2 }, { 'id': 3 }, { 'id': 4 } ]";
        assertEquals("application/json", response.getHeaders().get("Content-Type").get(0));
        JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
    }

    @Test
    void getTopEarnersInEachDepartment_basicTest_MultipleTopEarnerPerDepartment() {
        // TODO after fixing POST request test
    }

    @Test
    void getEmployeeNumberForEachDepartment_basicTest() throws JSONException {
        HttpHeaders headers = createHttpContentTypeHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = template.exchange(EMPLOYEE_COUNTER_URL, HttpMethod.GET, httpEntity, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());

        String expectedResponse = "[ { 'departmentName' : 'Finance', 'employeeCounter' : 1 } ," +
                "                   { 'departmentName' : 'IT', 'employeeCounter' : 2 }, " +
                "                   { 'departmentName': 'Admin', 'employeeCounter' : 1 } ]";

        assertEquals("application/json", response.getHeaders().get("Content-Type").get(0));
        JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
    }

    private HttpHeaders createHttpContentTypeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }


}
