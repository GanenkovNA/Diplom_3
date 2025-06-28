package ru.yandex.praktikum.stellarburgers_test.web;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.infrastructure.browser.DriverFactory;
import ru.yandex.praktikum.infrastructure.browser.Browser;
import ru.yandex.praktikum.stellarburgers_test.StellarBurgerBase;

public class WebBase extends StellarBurgerBase {
    public static final Browser BROWSER = Browser.CHROME;
    protected WebDriver driver;

    @Before
    public void setUpWeb() {
        driver = DriverFactory.getDriver(BROWSER);
    }

    @After
    public void tearDownWeb() {
        DriverFactory.closeDriver(driver);
    }

    protected void isPageLoaded(Runnable checkPage, String pageName){
        try{
            checkPage.run();
        } catch (Exception e){
            Assert.fail("Не произведён переход на страницу " + pageName + "!\nСтраница не загрузилась");
        }
    }
}
