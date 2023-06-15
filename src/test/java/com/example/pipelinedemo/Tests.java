package com.example.pipelinedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Tests {

    WebDriver driver;

    @AfterEach
    void tearDown(){
        //Will run at the end of every test, closing chrome driver
        driver.quit();
    }

    @BeforeEach
    void setUp(){
        //Will run before every test, instantiates a instance of chrome driver and opens up website
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://2itesting.com/");
    }

    @Test
    @Tag("Test1")
    void test1(){
        //This test opens up the main page, checks for 2 headings, clicks the contact link and checks for a heading there

        //Main page
        String header = driver.findElement(By.className(("promo-header"))).getText();
        String subHeading = driver.findElement(By.className(("sub-heading"))).getText();
        assertThat("Text doesn't match",header,containsString("Strategy"));
        assertThat("Text doesn't match",subHeading,containsString("Identify the risks to your Software Testing projects"));

        //Nav link
        WebElement contactLink = driver.findElement(By.xpath("//a[contains(text(),'Contact')]"));
        contactLink.click();

        //Contact page
        String contactHeader = driver.findElement(By.className(("page-header"))).getText();
        assertThat("Text doesn't match",contactHeader,containsString("Contact us"));
    }

    @Test
    @Tag("Test2")
    void test2(){
        //This test checks 2 different headings, then clicks a nav link on the page and checks a heading there

        //Main page
        String header2 = driver.findElement(By.xpath(("//*[@id=\"mp-pusher\"]/div[5]/div/div/div/div/div[2]/div/div[1]/h3"))).getText();
        String subHeading2 = driver.findElement(By.xpath(("//*[@id=\"mp-pusher\"]/div[5]/div/div/div/div/div[2]/div/div[1]/h2"))).getText();
        assertThat("Text doesn't match",header2,containsString("Project Delivery"));
        assertThat("Text doesn't match",subHeading2,containsString("A wide array of testing services can make up your bespoke expert team"));

        //Nav link
        WebElement link = driver.findElement(By.xpath("//a[contains(text(),'Test Consultancy')]"));
        link.click();

        //Test Consultancy page
        String consultancyHeader = driver.findElement(By.xpath("//*[@id=\"mp-pusher\"]/div[5]/div/div/h1")).getText();
        assertThat("Text doesn't match",consultancyHeader,containsString("Test Consultancy"));
    }

    @Test
    @Tag("Test3")
    void test3(){
        //This test closes the pop-up at the bottom of the page and then clicks a nav link, then checks a heading there

        //Pop-up dismiss
        WebElement dismiss = driver.findElement(By.xpath("//button[contains(text(),'close message')]"));
        dismiss.click();

        //Nav link
        WebElement services = driver.findElement(By.xpath("//a[contains(text(),'Integration Testing')]"));
        services.click();

        //Integration testing page
        String bodyTest = driver.findElement(By.tagName("body")).getText();
        assertThat("Text doesn't match",bodyTest,containsString("Synergy between applications and your system at large"));
    }
}