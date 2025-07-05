package ru.yandex.praktikum.test.burgers.web;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import ru.yandex.praktikum.infrastructure.browser.DriverFactory;
import ru.yandex.praktikum.infrastructure.browser.Browser;
import ru.yandex.praktikum.test.burgers.StellarBurgerBase;

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

    protected boolean isElementVisibleOnScreen(WebElement element) {
        try {
            // 1. Проверяем, что элемент видим (не скрыт CSS)
            if (!element.isDisplayed()) {
                return false;
            }

            // 2. Получаем координаты элемента
            org.openqa.selenium.Point location = element.getLocation();
            Dimension elementSize = element.getSize();

            // 3. Получаем размеры окна браузера
            Dimension windowSize = driver.manage().window().getSize();
            int windowWidth = windowSize.getWidth();
            int windowHeight = windowSize.getHeight();

            // 4. Проверяем, что элемент находится в пределах видимой области
            boolean isWithinViewportX = (location.getX() >= 0) &&
                    (location.getX() + elementSize.getWidth() <= windowWidth);

            boolean isWithinViewportY = (location.getY() >= 0) &&
                    (location.getY() + elementSize.getHeight() <= windowHeight);

            return isWithinViewportX && isWithinViewportY;
        } catch (NoSuchElementException e) {
            return false; // Элемент не найден в DOM
        }
    }
}
