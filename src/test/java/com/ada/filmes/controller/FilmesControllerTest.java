package com.ada.springtestremedios.controller;

import com.ada.springtestremedios.domain.Remedio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RemediosControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testInserePorApi() {
        // Crio um elemento de teste
        Remedio remedio = Remedio.builder()
                .nome("Paracetamol")
                .descricao("Analgésico")
                .preco(new BigDecimal(10))
                .build();

        // Está fazendo uma chamada de api.
        ResponseEntity<Remedio> remedioResposta = this.testRestTemplate
                .postForEntity("http://localhost:" + port + "/remedios",
                        remedio, Remedio.class);

        Assertions.assertEquals(HttpStatus.CREATED, remedioResposta.getStatusCode());
        Assertions.assertEquals("Paracetamol", remedioResposta.getBody().getNome());
        Assertions.assertEquals("Analgésico", remedioResposta.getBody().getDescricao());

    }

}
