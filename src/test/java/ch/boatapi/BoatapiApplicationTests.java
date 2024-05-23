package ch.boatapi;

import ch.boatapi.model.BoatDto;
import ch.boatapi.repository.boat.Boat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

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

    private static Stream<BoatDto> boatProvider() {
        return Stream.of(
                new BoatDto(null, "description"),
                new BoatDto("name", null)
        );
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
    @CsvSource({"19ba0988-2173-418f-b88e-d0fad90d0cef,Sailors Delight", "29ba0988-2173-418f-b88e-d0fad90d0cef,Ocean Explorer", "39ba0988-2173-418f-b88e-d0fad90d0cef,Lake Cruiser"})
    public void testGetBoatById(String id, String excepted) {
        Boat boat = restTemplate.getForObject(getRootUrl() + "/api/v1/boats/" + id, Boat.class);
        assertEquals("name should be equals", excepted, boat.getName());
    }

    @Test
    public void testCreateBoat(){
        BoatDto boatDto = new BoatDto("name", "description");

        ResponseEntity<BoatDto> response = restTemplate.postForEntity(getRootUrl() + "/api/v1/boats", boatDto, BoatDto.class);
        assertNotNull("should not be null",response.getBody());
        assertEquals("could be 200", 200, response.getStatusCode());
    }

    @ParameterizedTest
    @MethodSource("boatProvider")
    public void testCreateBoatValidation(BoatDto boatDto){
        ResponseEntity<BoatDto> response = restTemplate.postForEntity(getRootUrl() + "/api/v1/boats", boatDto, BoatDto.class);
        assertEquals("could be 400", 400, response.getStatusCode().value());
    }


}
