package co.werush.POM.pages.common;

import co.werush.POM.Application;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    private static Logger log;

    protected BasePage(Application app) {
        this.baseUrl = app.url;
        this.driver = app.getDriver();
        this.wait = app.getWait();
        log = LogManager.getLogger(this.getClass().getName());
        PageFactory.initElements(driver, this);
    }

    @Step("Verify is element present on the page: {element}")
    protected boolean isElementPresent(By selector) {
        try {
            driver.findElement(selector);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            log.info("Element is founded By selector: " + selector);
            return true;
        } catch (NoSuchElementException e) {
            log.info("Element not founded By selector" + selector);
            return false;
        }
    }

    @Step("Verify is element present on the page: {element}")
    protected boolean isElementPresent(WebElement element) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.findElement(By.xpath(element.toString()));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            log.info("Element is founded By selector: " + element);
            return true;
        } catch (NoSuchElementException e) {
            log.info("Element not founded By selector" + element);
            return false;
        }
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
        log.info("Click to button with locator: " + locator);
    }

    @Step("Click on web element: {element}")
    protected void click(WebElement element) {
        wait.until(elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        log.info("Clicked to element: " + element);
    }

    @Step("Get current url")
    public String getCurrentURL() {
        String currentUrl = driver.getCurrentUrl();
        log.info("Current url is: " + currentUrl);
        return currentUrl;
    }

    @Step("Go back to previous page")
    public void clickBack() {
        driver.navigate().back();
        log.info("Click to previous page");
    }
}
