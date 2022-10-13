package co.werush.tests;

import co.werush.POM.Application;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Tests {
    private final Application app = new Application();

    @BeforeSuite
    public void getHomePage() {
        app.slider().open();
    }

    @Test
    public void testSlidersButtons() {
        SoftAssert softAssert = new SoftAssert();
        for (String str : app.slider().getListOfSlidesButtons())
            softAssert.assertFalse(app.slider().ifRedirectionButtonsRedirectedToPage404(str).contains("/404"),
                    "Found page /404");
        softAssert.assertAll();
    }

    @AfterSuite
    public void quitDriver() {
        app.quitDriver();
    }
}
