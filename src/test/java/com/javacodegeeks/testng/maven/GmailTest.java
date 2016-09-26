package com.javacodegeeks.testng.maven;
/*      1) Залогиниться в первого
        2) Отправить письмо с заголовком и текстом второму аккаунту
        3) вылогиниться
        4) Залогиниться во второго
        5) Проверить что письмо от первого пришло и у него правильный заголовок и правильный текст письма
        6) Удалить письмо
        7) Вылогиниться
*/


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;


public class GmailTest {


    private static WebDriver driver = new FirefoxDriver();
    private String emailTitle = "Gmail " + System.currentTimeMillis();
    private String emailBody = "Test";

    @Test
    public void sendEmailTest() {
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com");

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement searchField = driver.findElement(By.name("Email"));
        searchField.clear();
        searchField.sendKeys("zigmundfreid1994");
        WebElement signInButton = driver.findElement(By.name("signIn"));
        signInButton.click();

        searchField = driver.findElement(By.id("Passwd"));
        searchField.clear();
        searchField.sendKeys("gazarog1994");
        WebElement signButton = driver.findElement(By.id("signIn"));
        signButton.click();

       WebElement netButton = driver.findElement(By.cssSelector("a.gb_b.gb_Vb"));
        netButton.click();
       WebElement mailButton = driver.findElement(By.id("ogbkddg:6"));
        mailButton.click();

        WebElement composeButton = driver.findElement(By.xpath("//div[@gh='cm']"));
        composeButton.click();

        searchField = driver.findElement(By.className("vO"));
        searchField.clear();
        searchField.sendKeys("havryshkostya@gmail.com");
        searchField = driver.findElement(By.name("subjectbox"));
        searchField.clear();

        searchField.sendKeys(emailTitle);
        searchField = driver.findElement(By.className("Am"));
        searchField.sendKeys(emailBody);
        WebElement sendButton = driver.findElement(By.xpath("//div[contains(text(),'Send')]"));
        sendButton.click();

        WebElement accountButton = driver.findElement(By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span"));
        accountButton.click();
        WebElement logOutButton = driver.findElement(By.xpath(".//*[@id='gb_71']"));
        logOutButton.click();

        if (isDialogPresent(driver))
            driver.switchTo().alert().accept();

    }


    @Test(dependsOnMethods = {"sendEmailTest"})
    public void receiveEmailTest() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


        WebElement searchField = driver.findElement(By.name("Email"));
        searchField.clear();
        searchField.sendKeys("havryshkostya@gmail.com");
        WebElement signButton = driver.findElement(By.name("signIn"));
        signButton.click();

        searchField = driver.findElement(By.name("Passwd"));
        searchField.clear();
        searchField.sendKeys("gazarog1994");
        signButton = driver.findElement(By.id("signIn"));
        signButton.click();

        WebElement netButton = driver.findElement(By.cssSelector("a.gb_b.gb_Vb"));
        netButton.click();
        WebElement mailButton = driver.findElement(By.id("ogbkddg:6"));
        mailButton.click();

        Assert.assertTrue(driver.getTitle().contains("Gmail"));

        WebElement emailLink =  driver.findElement(By.xpath("//span[contains(text(),'Gmail')]"));
        emailLink.click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Test')]")).isDisplayed());

        


    }


    private static boolean isDialogPresent(WebDriver driver) {
        try {

            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            // Modal dialog showed
            return false;
        }
    }
    @AfterMethod
public static void after(){
    driver.quit();
}
}



