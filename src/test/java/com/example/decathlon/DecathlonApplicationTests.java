package com.example.decathlon;

import com.example.decathlon.controller.DevController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DecathlonApplicationTests {
    @Autowired
    private DevController controller;
    @Value("${server.port}")

    private int randomServerPort;

    DecathlonApplicationTests() {
    }

    @Test
    public void contextLoads() throws Exception {


        assertThat(controller).isNotNull();
    }


    @Test
    public void testGetDevList() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8080/1";

        URI uri = new URI(baseUrl);


        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        System.out.println(result);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("alert sent"));
    }


}
