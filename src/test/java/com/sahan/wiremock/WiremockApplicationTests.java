package com.sahan.wiremock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WiremockApplicationTests {

    private int port = 8081;
    private String fakeServer = "http://localhost:" + port;
    private String body = "{\n" +
            "  \"id\": 1,\n" +
            "  \"firstName\": \"Sahan\",\n" +
            "  \"lastName\": \"Ekanayake\",\n" +
            "  \"email\": \"sahan@gmail.com\",\n" +
            "}";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(port);

    @Test
    @Description("Test Employee")
    public void testEmployee() {
        stubFor(get(urlPathMatching("/employee/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)
                )
        );

        RestTemplate restTemplate = new RestTemplate();
        String resourceURL = fakeServer;
        ResponseEntity<String> response = restTemplate
                .getForEntity(resourceURL + "/employee/1", String.class);

        assertNotNull(response);
        assertTrue("Status code not equals to 200", response.getStatusCode().equals(HttpStatus.OK));
        assertTrue("Contains fail", response.getBody().contains("\"firstName\": \"Sahan\""));
        assertTrue("Contains fail", response.getBody().contains("\"lastName\": \"Ekanayake\""));
        assertTrue("Contains fail", response.getBody().contains("\"email\": \"sahan@gmail.com\""));
    }
}