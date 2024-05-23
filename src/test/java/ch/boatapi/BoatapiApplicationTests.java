package ch.boatapi;

import ch.boatapi.repository.boat.Boat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest(classes = BoatapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoatapiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetAllBoats() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/boats",
                HttpMethod.GET, entity, String.class);

        assertNotNull("response should not be null", response.getBody());
    }

    @ParameterizedTest
    @CsvSource({"1,Sailors Delight", "2,Ocean Explorer", "3,Lake Cruiser"})
    public void testGetBoatById(String id, String excepted) {
        Boat boat = restTemplate.getForObject(getRootUrl() + "/api/v1/boats/" + id, Boat.class);
        assertEquals("name should be equals", excepted, boat.getName());
    }


}
