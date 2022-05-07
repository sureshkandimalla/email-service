FROM openjdk:11.0.3-jdk-slim-stretch

ARG JAR_FILE=target/email-service-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} email-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/email-service.jar"]
