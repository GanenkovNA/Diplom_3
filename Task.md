# Задание 3. Автотесты для UI
В последнем задании тебе нужно протестировать [веб-приложение](https://stellarburgers.nomoreparties.site/) Stellar Burgers.

## Что нужно сделать
1. Опиши элементы, которые будешь использовать в тестах, с помощью Page Object.
2. Протестируй функциональность в Google Chrome и Яндекс Браузере. Подключи Allure-отчёт.

### Регистрация
Проверь:
- [x] Успешную регистрацию.
- [x] Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
### Вход
Проверь:
- [*] вход по кнопке «Войти в аккаунт» на главной;
- [*] вход через кнопку «Личный кабинет»;
- [*] вход через кнопку в форме регистрации;
- [*] вход через кнопку в форме восстановления пароля.
### Раздел «Конструктор»
Проверь, что работают переходы к разделам:
- [ ] «Булки»;
- [ ] «Соусы»;
- [ ] «Начинки».