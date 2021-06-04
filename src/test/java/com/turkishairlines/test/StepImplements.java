package com.turkishairlines.test;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StepImplements extends HookImplements {


    @Step("Wait <value> seconds")
    public void waitTwoSeconds(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }



    @Step("Select random flight on the screen with <list>")
    public void selectRandomFlightOnTheScreen(String key) throws InterruptedException {
        String value= parser(key);
        List<WebElement> flightList = driver.findElements(By.id(value));
        Random r = new Random();
        int random = r.nextInt(flightList.size());
        flightList.get(random).click();
    }


    @Step("<key> ID li elemente tıkla")
    public void clickIdButtonIml(String key)
    {
        String value = parser(key);
        driver.findElement(By.id(value)).click();
    }
    @Step("<key> Xpath li elemente tıkla")
    public void clickXpathButtonIml(String key)
    {
        String value = parser(key);
        driver.findElement(By.xpath(value)).click();
    }

    @Step("<key> elementine <text> degerini gir")
    public void sendKeyButton(String key, String keyText){
        String value = parser(key);
        driver.findElement(By.id(value)).sendKeys(keyText);
    }

    @Step("Set departure date from <key>")
    public void setDepartureDate(String keyCurent) throws InterruptedException {
        String value= parser(keyCurent);
        String currentDate = driver.findElement(By.id(value)).getText().trim();
        driver.findElement(By.id(value)).click();
        int departureDate = Integer.parseInt(currentDate);
        departureDate += 2;
        driver.findElement(By.xpath("//*[@text=" + departureDate + "]")).click();

    }



}
