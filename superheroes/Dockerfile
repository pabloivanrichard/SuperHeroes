FROM openjdk:8-jdk-alpine
MAINTAINER prichard.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} superheroes-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/superheroes-0.0.1-SNAPSHOT.jar"]