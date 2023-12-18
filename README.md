## Task Management System

Это простое руководство поможет вам запустить проект. Мы используем Java, Spring Boot, PostgreSQL и Keycloak.

### Что вам понадобится

- Java 17+
- Docker и Docker Compose

### Шаги

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

    После запуска, откройте веб-браузер и перейдите по адресу:

    [http://localhost:8080/](http://localhost:8080/)

6. **Keycloak:**

    После запуска, дождитесь полного запуска Keycloak и откройте Keycloak Admin UI по адресу:

    [http://localhost:8000/](http://localhost:8000/)

    Войдите с учетными данными из файла `docker-compose.yml`.

7. **Остановите контейнеры:**

    Когда закончите работу с проектом, выполните:

    ```bash
    docker-compose down
    ```
