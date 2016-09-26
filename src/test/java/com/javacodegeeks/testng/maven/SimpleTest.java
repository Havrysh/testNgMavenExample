package com.javacodegeeks.testng.maven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class SimpleTest {
    @Test
    public void navigateToRozetkaAndSearcForIphone() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://rozetka.com.ua");

        WebElement searchField = driver.findElement(By.className("header-search-input-text"));
        searchField.clear();
        searchField.sendKeys("iPhone");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement searchButton = driver.findElement(By.className("btn-link-i"));
        searchButton.click();
    }


}


