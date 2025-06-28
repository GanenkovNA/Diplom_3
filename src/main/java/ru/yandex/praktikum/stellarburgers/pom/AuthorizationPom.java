package ru.yandex.praktikum.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.stellarburgers.BrowserConfig.DEFAULT_WAIT_TIME;

public class AuthorizationPom extends ConstructorPom{
    private static final String pageName = "Вход (страница авторизации)";
    public static final String AUTH_PATH = BASE_URI + "/login";

    //Локаторы формы регистрации
    private static final By authorizationTitleLocator = By.xpath("//h2[contains(text(), 'Вход')]");
    private static final By emailInputFieldLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//label[contains(text(), 'Email')]/following-sibling::input");
    private static final By passwordInputFieldLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//label[contains(text(), 'Email')]/following-sibling::input");
    private static final By authorizationButtonLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//button[contains(text(), 'Войти')]");

    public AuthorizationPom(WebDriver driver) {
        super(driver);
    }

    public void isAuthorizationPageLoaded(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(authorizationTitleLocator));
    }

    public void openAuthorizationPage(){
        driver.get(AUTH_PATH);
        isAuthorizationPageLoaded();
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

    public void fillOutAuthorizationFormAndClick(String email, String password){
        inputEmail(email);
        inputPassword(password);
        clickOnAuthorizationButton();
    }

    public String getPageName(){
        return pageName;
    }
}
