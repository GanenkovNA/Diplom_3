package ru.yandex.praktikum.burgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.burgers.pom.base.HeaderPom;
import ru.yandex.praktikum.burgers.pom.base.PageMethods;

import java.time.Duration;
import java.util.List;

import static ru.yandex.praktikum.burgers.browser.BrowserConfig.DEFAULT_WAIT_TIME;

public class RegisterPom extends HeaderPom implements PageMethods {
    private static final String pageName = "Регистрация";
    public static final String PATH = BASE_URI + "/register";

    private static final By authButtonLocator = By.xpath("//a[contains(@class, 'Auth_link') and contains(text(), 'Войти')]");
    //Локаторы формы регистрации
    private static final By registrationTitleLocator = By.xpath("//h2[contains(text(), 'Регистрация')]");
    private static final By nameInputFieldLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//label[contains(text(), 'Имя')]/following-sibling::input");
    private static final By emailInputFieldLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//label[contains(text(), 'Email')]/following-sibling::input");
    private static final By passwordInputFieldLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//input[@type='password']");
    private static final By passwordErrorLocator = By.xpath("//p[contains(@class, 'input__error') and contains(text(), 'Некорректный пароль')]");
    private static final By registerButtonLocator = By.xpath("//form[contains(@class, 'Auth_form_')]//button[contains(text(), 'Зарегистрироваться')]");

    public RegisterPom(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationTitleLocator));
    }

    @Override
    public void openPage() {
        driver.get(PATH);
        isPageLoaded();
    }

    public void inputName(String name){
        driver.findElement(nameInputFieldLocator).sendKeys(name);
    }

    public void inputEmail(String email){
        driver.findElement(emailInputFieldLocator).sendKeys(email);
    }

    public void inputPassword(String password){
        driver.findElement(passwordInputFieldLocator).sendKeys(password);
    }

    public void clickOnRegisterButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(registerButtonLocator))
                .click();
    }

    public void clickAuthButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(authButtonLocator))
                .click();
    }

    public boolean isPasswordErrorDisplayed(){
        List<WebElement> elements = driver.findElements(passwordErrorLocator);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    @Override
    public String getPageName(){
        return pageName;
    }
}
