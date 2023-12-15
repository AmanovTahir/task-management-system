## Task Management System

Это простое руководство поможет вам запустить проект 
Мы используем Java, Spring Boot, PostgreSQL и Keycloak.

### Что вам понадобится

- Java 17+ 
- Docker и Docker Compose

### Шаги

1. **Скачайте проект:**

    ```bash
    git clone <https://github.com/AmanovTahir/task-management-system>
    ```

2. **Docker Compose:**

    контейнеры для PostgreSQL, Keycloak и Spring Boot уже определены в файле. [docker-compose.yml](docker-compose.yml)

3. **Соберите Docker образ:**
    
     В корневой папке проекта файл с именем [Dockerfile](Dockerfile) выполните команду сборки Docker образа:

4. **Запустите контейнеры:**
    
    ```bash
    docker-compose up -d
    ```
   
    Это создаст и запустит контейнера: PostgreSQL, Keycloak и ваше Spring Boot приложение.

5. **Swagger API:**

    После запуска, откройте веб-браузер и перейдите по адресу:

    [http://localhost:8080/](http://localhost:8080/)

6. **Keycloak:**

    После запуска, дождитесь полноного запуска keycloak и откройте Keycloak UI по адресу:

    [http://localhost:8000/](http://localhost:8000/)

    Войдите с учетными данными из файла `docker-compose.yml`.

8. **Остановите контейнеры:**

    Когда закончите работу с проектом, выполните:

    ```bash
    docker-compose down
    ```