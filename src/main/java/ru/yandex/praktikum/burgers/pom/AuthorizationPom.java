package ru.yandex.praktikum.burgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.burgers.pom.base.HeaderPom;
import ru.yandex.praktikum.burgers.pom.base.PageMethods;

import java.time.Duration;

import static ru.yandex.praktikum.burgers.browser.BrowserConfig.DEFAULT_WAIT_TIME;

public class AuthorizationPom extends HeaderPom implements PageMethods {
    private static final String pageName = "Вход (страница авторизации)";
    public static final String PATH = BASE_URI + "/login";

    //Локаторы формы регистрации
    private static final By authorizationTitleLocator = By.xpath("//h2[contains(text(), 'Вход')]");
    private static final By emailInputFieldLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//label[contains(text(), 'Email')]/following-sibling::input");
    private static final By passwordInputFieldLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//label[contains(text(), 'Пароль')]/following-sibling::input");
    private static final By authorizationButtonLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//button[contains(text(), 'Войти')]");

    public AuthorizationPom(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(authorizationTitleLocator));
    }

    @Override
    public void openPage() {
        driver.get(PATH);
        isPageLoaded();
    }

    public void inputEmail(String email){
        driver.findElement(emailInputFieldLocator).sendKeys(email);
    }

    public void inputPassword(String password){
        driver.findElement(passwordInputFieldLocator).sendKeys(password);
    }

    public void clickOnAuthorizationButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(authorizationButtonLocator))
                .click();
    }

    @Override
    public String getPageName(){
        return pageName;
    }
}
