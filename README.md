# Автотесты для UI

Проект третьей части диплома по автоматизации тестирования курса "Яндекс.Практикум". Задание описано в `Task.md`. 

Для смены запускаемого браузера необходимо в классе `WebBase.java` изменить значение переменной `BROWSER`. Размер задержек по умолчанию задан в `BrowserConfig.java`.

В проекте использованы следующие инструменты:
* `Selenium 4.33.0`
* `WebDriverManager 6.1.0`
* `Junit 4 4.13.2`
* `RestAssured 5.5.5`
* `Project Lombok 1.18.32`
* `Jackson Databind 2.19.0`
* `AssertJ Core 3.27.3`
* `Data Faker 1.8.1`

Для генерации отчёта:
* `Allure JUnit4 2.29.1`
* `Allure RestAssured 2.29.1`
* `Maven Surefire Plugin 3.5.3`
* `AspectJ Weaver 1.9.24`
* `Allure Maven 2.15.2`