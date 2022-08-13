FROM openjdk:17-jdk-slim
RUN adduser --system --group spring
USER spring:spring

ARG JAR_FILE=--from=build
COPY ${JAR_FILE} app.jar

RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Xmx512m","-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${PORT}","-Dspring.profiles.active=prod","-jar", "/app.jar" ]