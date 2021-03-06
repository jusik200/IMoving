package com.imoving.UI.methods;

import com.imoving.UI.Log4jDemo;
import com.imoving.UI.utils.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Helper {

    private static Logger logger = LogManager.getLogger(Helper.class);


    public static void click(WebElement element) {
        try {
            logger.info("Trying to click " + element);
            waitElementToBeDisplayed(element);
            waitElementToBeClickable(element);
            navigateToElement(element);
            element.click();
            logger.info("Element is clicked " + element);
        } catch (ElementClickInterceptedException e) {
            logger.warn("Element is not clickable without JSExecutor " + element);
            clickJSExecutor(element);
        }

    }

    public static void waitElementToBeDisplayed(WebElement element) {
        logger.debug("Waiting for element to be displayed " + element);
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitElementToBeClickable(WebElement element) {
        logger.debug("Waiting for element to be clickable " + element);
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }


    public static void sendKeys(WebElement element, String value) {
        element.sendKeys(value);
    }

    public static void sendKeys(WebElement element, Keys button) {
        element.sendKeys(button);
    }

    public static void pause(Integer milliseconds) {
        logger.info("Pausing th System for " + milliseconds);
        try {
            logger.debug("Trying to pause the System");
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            logger.error("Can not pause the System");
            e.printStackTrace();
        }
    }

    public static void selectByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public static void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public static String getTextFromSelected(WebElement element) {
        logger.info("Get text value from " + element);
        waitElementToBeDisplayed(element);
        return element.getText();
    }

    public static void clickJSExecutor(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click()", element);
    }

    public static void navigateToElement(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void multiClick(WebElement element, int howManyTimes) {
        waitElementToBeDisplayed(element);
        waitElementToBeClickable(element);
        for (int i = 0; howManyTimes > i; i++) {
            click(element);
        }
    }


}
