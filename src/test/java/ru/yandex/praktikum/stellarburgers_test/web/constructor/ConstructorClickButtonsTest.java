package ru.yandex.praktikum.stellarburgers_test.web.constructor;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.pom.ConstructorPom;
import ru.yandex.praktikum.stellarburgers_test.web.WebBase;

public class ConstructorClickButtonsTest extends WebBase {
    private ConstructorPom constructorPage;
    private final SoftAssertions soft = new SoftAssertions();

    @Before
    public void setUpTest(){
        constructorPage = new ConstructorPom(driver);
    }

    @Test
    public void shouldShowIngredients(){
        constructorPage.openPage();
        constructorPage.isPageLoaded();

        constructorPage.clickFillingButton();
        soft.assertThat(constructorPage.isFillingButtonActive())
                .as("Проверка, что кнопка \"Начинка\" активна")
                .isTrue();
        soft.assertThat(
                isElementVisibleOnScreen(constructorPage.isFillingListHeaderDisplayed()))
                .as("Проверка, что заголовок \"Начинка\" отображается на экране")
                .isTrue();

        constructorPage.clickSauceButton();
        soft.assertThat(constructorPage.isSauceButtonActive())
                .as("Проверка, что кнопка \"Соус\" активна")
                .isTrue();
        soft.assertThat(
                isElementVisibleOnScreen(constructorPage.isSauceListHeaderDisplayed()))
                .as("Проверка, что заголовок \"Соус\" отображается на экране")
                .isTrue();

        constructorPage.clickBunButton();
        soft.assertThat(constructorPage.isBunButtonActive())
                .as("Проверка, что кнопка \"Булки\" активна")
                .isTrue();
        soft.assertThat(
                        isElementVisibleOnScreen(constructorPage.isBunListHeaderDisplayed()))
                .as("Проверка, что заголовок \"Булки\" отображается на экране")
                .isTrue();

        soft.assertAll();
    }
}
