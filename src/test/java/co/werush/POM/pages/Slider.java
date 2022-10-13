package co.werush.POM.pages;

import co.werush.POM.Application;
import co.werush.POM.pages.common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashSet;
import java.util.List;

public class Slider extends BasePage {

    private final By activeElement = By.xpath("//app-rush-slider//div[@class='track']//app-rush-slide[@class='home-slider__slide-wrapper ng-star-inserted active']//a");
    @FindBy(xpath = "//i[@class='home-slider__next']")
    private WebElement nextSlide;
    @FindBy(xpath = "//ul[@class='dots']//li")
    private List<WebElement> buttons;

    public Slider(Application app) {
        super(app);
    }

    @Step("Users accessed to Sign In Page")
    public void open() {
        driver.get(baseUrl);
    }

    @Step("Get list of buttons on sliders")
    public LinkedHashSet<String> getListOfSlidesButtons() {
        String xpath = "";
        LinkedHashSet<String> listOfXpathButtonsOnSlides = new LinkedHashSet<>();
        for (int i = 0; i < buttons.size(); i++) {
            xpath = driver.findElement(activeElement).getAttribute("href");
            listOfXpathButtonsOnSlides.add(activeElement
                    .toString()
                    .substring(10) + "[@href='" + xpath.substring(38) + "']");
            click(nextSlide);
        }
        return listOfXpathButtonsOnSlides;
    }

    @Step("If was redirected to page /404")
    public String ifRedirectionButtonsRedirectedToPage404(String string) {
        String currentUrl = "";
        isElementPresent(By.xpath(string));
        click(By.xpath(string));
        currentUrl = getCurrentURL();
        clickBack();
        click(nextSlide);
        return currentUrl;
    }
}
