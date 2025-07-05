package ru.yandex.praktikum.test.burgers.web.constructor.igredients.buttons.positive;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.burgers.pom.ConstructorPom;
import ru.yandex.praktikum.test.burgers.web.WebBase;

import static io.qameta.allure.Allure.step;

@DisplayName("Проверка работы кнопок ингредиентов конструктора")
public class ConstructorClickButtonsTest extends WebBase {
    private ConstructorPom constructorPage;
    private final SoftAssertions soft = new SoftAssertions();

    @Before
    public void setUpTest(){
        constructorPage = new ConstructorPom(driver);

        step("Открытие домашней страницы",() -> {
            constructorPage.openPage();
            isPageLoaded(constructorPage::isPageLoaded, constructorPage.getPageName());
        });
    }

    @Test
    @DisplayName("Проверка работы кнопки \"Начинка\"")
    @Description("При нажатии:\n" +
            "* кнопка должна становиться активной\n" +
            "* список должен прокручиваться на выбранную категорию")
    public void shouldShowFillingIngredientsTest(){
        step("Нажатие на кнопку \"Начинка\"",
                constructorPage::clickFillingButton);

        step("Проверка работы кнопки \"Начинка\"", () -> {
            soft.assertThat(
                            isElementVisibleOnScreen(constructorPage.isFillingListHeaderDisplayed()))
                    .as("Проверка, что заголовок \"Начинка\" отображается на экране")
                    .isTrue();
            soft.assertThat(constructorPage.isFillingButtonActive())
                    .as("Проверка, что кнопка \"Начинка\" активна")
                    .isTrue();
        });
        soft.assertAll();
    }

    @Test
    @DisplayName("Проверка работы кнопки \"Соус\"")
    @Description("При нажатии:\n" +
            "* кнопка должна становиться активной\n" +
            "* список должен прокручиваться на выбранную категорию")
    public void shouldShowSauceIngredientsTest(){
        step("Нажатие на кнопку \"Соус\"",
                constructorPage::clickSauceButton);

        step("Проверка работы кнопки \"Соус\"", () -> {

            soft.assertThat(
                            isElementVisibleOnScreen(constructorPage.isSauceListHeaderDisplayed()))
                    .as("Проверка, что заголовок \"Соус\" отображается на экране")
                    .isTrue();
            soft.assertThat(constructorPage.isSauceButtonActive())
                    .as("Проверка, что кнопка \"Соус\" активна")
                    .isTrue();
        });
        soft.assertAll();
    }

    @Test
    @DisplayName("Проверка работы кнопки \"Булки\"")
    @Description("При нажатии:\n" +
            "* кнопка должна становиться активной\n" +
            "* список должен прокручиваться на выбранную категорию")
    public void shouldShowBunIngredientsTest(){

        step("Снятие активного статуса с кнопки \"Булки\"",
                constructorPage::clickFillingButton);

        step("Нажатие на кнопку \"Булки\"",
                constructorPage::clickBunButton);

        step("Проверка работы кнопки \"Булки\"", () -> {
            soft.assertThat(isElementVisibleOnScreen(constructorPage.isBunListHeaderDisplayed()))
                    .as("Проверка, что заголовок \"Булки\" отображается на экране")
                    .isTrue();
            soft.assertThat(constructorPage.isBunButtonActive())
                    .as("Проверка, что кнопка \"Булки\" активна")
                    .isTrue();
        });
        soft.assertAll();
    }
}
