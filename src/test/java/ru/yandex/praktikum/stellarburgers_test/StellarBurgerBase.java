package ru.yandex.praktikum.stellarburgers_test;

import io.restassured.RestAssured;
import org.junit.Before;

import static ru.yandex.praktikum.stellarburgers.pom.ConstructorPom.BASE_URI;

public class StellarBurgerBase {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }
}
