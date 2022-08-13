FROM maven:3.6.0-jdk-11-slim AS build
RUN mvn install jar:jar \
    && cp -a target/*.jar app.jar \
    && rm -rf target "$HOME/.m2"


FROM openjdk:17-jdk-slim

RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Xmx512m","-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${PORT}","-Dspring.profiles.active=prod","-jar", "/app.jar" ]