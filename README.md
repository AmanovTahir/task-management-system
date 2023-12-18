# Task Management System

Это руководство поможет вам запустить проект Task Management System, использующий Java, Spring Boot, PostgreSQL и Keycloak.

## Требования

- Java 17+
- Docker и Docker Compose

## Шаги

1. **Скачайте проект:**

    ```bash
    git clone https://github.com/AmanovTahir/task-management-system
    ```

2. **Сборка Maven:**

    Перейдите в корневую папку проекта и выполните следующие команды для сборки проекта с использованием Maven:

    ```bash
    cd task-management-system
    mvn clean install
    ```

    Это создаст JAR-файл в папке `target`.

3. **Docker Compose:**

    Контейнеры для PostgreSQL, Keycloak и Spring Boot уже определены в файле [docker-compose.yml](docker-compose.yml).

4. **Запустите контейнеры:**
    
    ```bash
    docker-compose up -d
    ```
   
    Это создаст и запустит контейнеры: PostgreSQL, Keycloak и ваше Spring Boot приложение.

5. **Swagger API:**

    После запуска, откройте веб-браузер и перейдите по адресу: [http://localhost:8080/](http://localhost:8080/)

6. **Keycloak:**

    После запуска, дождитесь полного запуска Keycloak и откройте Keycloak Admin UI по адресу: [http://localhost:8000/](http://localhost:8000/)

    a) Создайте пользователя в Keycloak:
       Перейдите в раздел `Users` в левом меню и создайте пользователя с именем user1, адресом электронной почты `user1@example.com`. Во вкладке `Credentials` установите пароль `1` и Temporary = off.
    
    b) Запросите токен через Postman:
       Откройте Postman и создайте новый запрос с параметрами:

       Метод: POST
       URL: http://localhost:8000/realms/tasks/protocol/openid-connect/token
       Тип тела запроса: x-www-form-urlencoded
       Body (x-www-form-urlencoded):
         - grant_type: password
         - client_id: login-app
         - username: user1@example.com
         - password: 1
       
       Отправьте запрос, и вы получите ответ с токеном.

7. **Использование Swagger с токеном:**

   После получения токена из Keycloak, вы можете использовать Swagger для отправки запросов на сервер.

   - Откройте Swagger UI в веб-браузере по адресу: [http://localhost:8080/](http://localhost:8080/)
   - Выберите эндпоинт, который вы хотите протестировать.
   - В верхней части страницы найдите кнопку `Authorize` и введите ваш токен.
   - Теперь вы можете отправлять запросы, и Swagger будет автоматически включать ваш токен в заголовок авторизации.
   
       ***Примечание: Убедитесь, что токен активен и не истек. Если токен устарел, повторите шаги для получения нового токена из Keycloak.***

8. **Остановите контейнеры:**

    Когда закончите работу с проектом, выполните:

    ```bash
    docker-compose down
    ```
