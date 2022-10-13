package co.werush.POM;

import co.werush.POM.pages.Slider;
import co.werush.utils.DriverFactory;
import co.werush.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Application {

    public final String url;
    private WebDriver driver;
    private WebDriverWait wait;
    private Slider slider;

    public Application() {
        url = PropertyReader.applicationProperties().getProperty("base.url");
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = new DriverFactory().getDriver();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        return driver;
    }

    public WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return wait;
    }

    public void quitDriver() {
        driver.quit();
    }

    public Slider slider() {
        if (slider == null) {
            slider = new Slider(this);
        }
        return slider;
    }
}
