FROM bellsoft/liberica-openjdk-alpine-musl:17
COPY /target/task-*.jar /tasks-ms/tasks-ms.jar
ENTRYPOINT ["java", "-jar", "/tasks-ms/tasks-ms.jar"]