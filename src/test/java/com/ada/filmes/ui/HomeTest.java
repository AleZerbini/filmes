package com.ada.filmes.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomeTest {

    private WebDriver webDriver;

    @BeforeAll
    public static void setUpWebDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
    }

    @Test
    public void listFilmes() {
        webDriver = new ChromeDriver();

        webDriver.get("http://localhost:8080/home");

        String titulo = webDriver.getTitle();

        Assertions.assertEquals("Filmes", titulo);

        WebElement nome = webDriver.findElement(By.name("nome"));
        String valorNome = nome.getText();

        WebElement descricao = webDriver.findElement(By.name("genero"));
        String valorDescricao = descricao.getText();

        Assertions.assertEquals("teste2", valorNome);
        Assertions.assertEquals("teste2", valorDescricao);
        Assertions.assertEquals("1.00", valorPreco);

        webDriver.quit();
    }

    @Test
    public void insereFilme() {
        webDriver = new ChromeDriver();

        webDriver.get("http://localhost:8080/create");

        WebElement nome = webDriver.findElement(By.name("nome"));
        nome.sendKeys("tes");
        WebElement genero = webDriver.findElement(By.name("genero"));
        descricao.sendKeys("tes");

        WebElement submit = webDriver.findElement(By.id("submitButton"));
        submit.click();


        String titulo = webDriver.getTitle();
        Assertions.assertEquals("Filmes", titulo);

        WebElement nomeInserido = webDriver.findElement(By.name("nome"));
        WebElement generoInserido = webDriver.findElement(By.name("genero"));

        Assertions.assertEquals("tes", nomeInserido.getText());
        Assertions.assertEquals("tes", generoInserido.getText());

        webDriver.quit();
    }
}
