package ru.yandex.praktikum.stellarburgers_test;

import io.restassured.RestAssured;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.infrastructure.browser.DriverFactory;

public class StellarBurgerBase {
    public static final String BROWSER = "yandex";
    protected WebDriver driver;
    protected static final String BASE_URI="https://stellarburgers.nomoreparties.site";
    protected final SoftAssertions softly = new SoftAssertions();

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver(BROWSER);
        RestAssured.baseURI = BASE_URI;
    }

    @After
    public void tearDown() {
        DriverFactory.closeDriver(driver);
    }
}
