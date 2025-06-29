package ru.yandex.praktikum.test.burgers;

import io.restassured.RestAssured;
import org.junit.Before;

import static ru.yandex.praktikum.burgers.pom.ConstructorPom.BASE_URI;

public class StellarBurgerBase {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }
}
