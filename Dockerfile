FROM openjdk:17-jdk-slim
ARG JAR_FILE=no-loose-coins/target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod","-jar", "/app.jar" ]