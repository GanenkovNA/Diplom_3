package ru.yandex.praktikum.burgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.burgers.pom.base.HeaderPom;
import ru.yandex.praktikum.burgers.pom.base.PageMethods;

import java.time.Duration;
import java.util.Objects;

import static ru.yandex.praktikum.burgers.browser.BrowserConfig.DEFAULT_WAIT_TIME;

public class ConstructorPom extends HeaderPom implements PageMethods {
    private static final String pageName = "Домашняя страница (конструктор)";
    private static final String PATH = BASE_URI;

    // Блок сбора бургера
    private static final By ingredientsBlockLocator = By.xpath("//section[contains(@class, 'BurgerIngredients_ingredients')]");
    private static final By bunButtonLocator = By.xpath("//div[contains(@class, 'tab_tab') and .//span[contains(text(), 'Булки')]]");
    private static final By sauceButtonLocator = By.xpath("//div[contains(@class, 'tab_tab') and .//span[contains(text(), 'Соусы')]]");
    private static final By fillingButtonLocator = By.xpath("//div[contains(@class, 'tab_tab') and .//span[contains(text(), 'Начинки')]]");
    private static final By bunListHeaderLocator = By.xpath("//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[contains(text(), 'Булки')]");
    private static final By sauceListHeaderLocator = By.xpath("//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[contains(text(), 'Соусы')]");
    private static final By fillingListHeaderLocator = By.xpath("//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[contains(text(), 'Начинки')]");

    private static final By authButtonLocator = By.xpath("//button[contains(@class, 'button_button') and contains(text(), 'Войти в аккаунт')]");

    public ConstructorPom(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(ingredientsBlockLocator));
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

    public void clickBunButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(bunButtonLocator))
                .click();
    }

    public boolean isBunButtonActive(){
        return Objects.requireNonNull(driver.findElement(bunButtonLocator)
                .getAttribute("class")).contains("tab_tab_type_current");
    }

    public WebElement isBunListHeaderDisplayed(){
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(bunListHeaderLocator));
    }

    public void clickSauceButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(sauceButtonLocator))
                .click();
    }

    public boolean isSauceButtonActive(){
        return Objects.requireNonNull(driver.findElement(sauceButtonLocator)
                .getAttribute("class")).contains("tab_tab_type_current");
    }

    public WebElement isSauceListHeaderDisplayed(){
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceListHeaderLocator));
    }

    public void clickFillingButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(fillingButtonLocator))
                .click();
    }

    public boolean isFillingButtonActive(){
        return Objects.requireNonNull(driver.findElement(fillingButtonLocator)
                .getAttribute("class")).contains("tab_tab_type_current");
    }

    public WebElement isFillingListHeaderDisplayed(){
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingListHeaderLocator));
    }

    @Override
    public String getPageName(){
        return pageName;
    }
}
