package com.udea.lab2cicd.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataControllerTest {

    @Autowired
    DataController controller;

    @Test
    void healthCheck() {
        assertEquals("Project is online", controller.healthCheck());
    }

    @Test
    void version(){
        assertEquals("This is version 0.0.1", controller.version());
    }

    @Test
    void nationLenght(){
        Integer nationsLenght = controller.getRandomNation().size();
        assertEquals(10,nationsLenght);
    }

    @Test
    void currenciesLenght(){
        Integer currenciesLenght = controller.getRandomCurrencies().size();
        assertEquals(20,currenciesLenght);
    }

    @Test
    public void testRandomCurrenciesCodeFormat(){
        DataController controller = new DataController();
        JsonNode response = controller.getRandomCurrencies();

        for(int i=0; i< response.size(); i++){
            JsonNode currency = response.get(i);
            String code = currency.get("code").asText();
            assertTrue(code.matches("[A-Z]{3}"));

        }
    }

    @Test
    public void testRandomNationsPerformance(){
        DataController controller = new DataController();
        long startTime= System.currentTimeMillis();
        controller.getRandomNation();
        long endTime= System.currentTimeMillis();
        long excecutionTime= endTime-startTime;
        assertTrue(excecutionTime< 2000);
    }

    @Test
    void aviationLenght(){
        Integer aviationLenght = controller.getRandomAviation().size();
        assertEquals(15, aviationLenght);
    }

}