# Чтобы запустить тесты и сгенерировать Allure отчёт:

* Запустите `mvn clean test`

## Чтобы получить доступ к отчету вручную:
1. Создайте отчет, запустив: `mvn allure:report`
2. Используйте HTTP-сервер для предоставления содержимого `target/site/allure-maven-plugin/` <br/>
   В [Intellij IDEA](https://www.jetbrains.com/idea/) вы можете получить доступ к отчету, открыв `target/site/allure-maven-plugin/index.html` в [веб-браузере](https://www.jetbrains.com/help/idea/configuring-third-party-tools.html#web-browsers).

## Для автоматического доступа к отчету:
Чтобы создать и открыть отчет в браузере по умолчанию, перейдите по адресу:
+ Случайный порт, запустить: `mvn allure:serve`
+ Конкретный порт, запустите: `mvn allure:serve -Dallure.serve.port={your_port}`