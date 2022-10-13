package co.werush.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public WebDriver getDriver() {
        String browserName = PropertyReader.applicationProperties().getProperty("browser");
        switch (browserName.toLowerCase()) {
            case "chrome"-> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default -> throw new RuntimeException("ATF is not configured for " + browserName);
        }
    }
}
