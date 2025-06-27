package ru.yandex.praktikum.stellarburgers_test;

import io.restassured.RestAssured;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.infrastructure.browser.DriverFactory;

import static ru.yandex.praktikum.stellarburgers.pom.HeaderPom.BASE_URI;

public class StellarBurgerBase {
    public static final String BROWSER = "chrome";
    protected WebDriver driver;
    protected final SoftAssertions softly = new SoftAssertions();

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver(BROWSER);
        RestAssured.baseURI = BASE_URI;
    }

    //@After
    public void tearDown() {
        DriverFactory.closeDriver(driver);
    }
}
