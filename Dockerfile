FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/beyondApp.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","app.jar"]