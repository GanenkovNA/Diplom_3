package ru.yandex.praktikum.burgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.burgers.pom.base.HeaderPom;
import ru.yandex.praktikum.burgers.pom.base.PageMethods;

import java.time.Duration;

import static ru.yandex.praktikum.burgers.browser.BrowserConfig.DEFAULT_WAIT_TIME;

public class ForgottenPasswordPom extends HeaderPom implements PageMethods {
    private static final String pageName = "Восстановление пароля";
    public static final String PATH = BASE_URI + "/forgot-password";

    private static final By forgottenPasswordTitleLocator = By.xpath("//h2[contains(text(), 'Восстановление пароля')]");
    private static final By authButtonLocator = By.xpath("//a[contains(@class, 'Auth_link') and contains(text(), 'Войти')]");

    public ForgottenPasswordPom(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(forgottenPasswordTitleLocator));
    }

    @Override
    public void openPage() {
        driver.get(PATH);
        isPageLoaded();
    }

    public void clickAuthButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(authButtonLocator))
                .click();
    }

    @Override
    public String getPageName(){
        return pageName;
    }
}
