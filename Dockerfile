FROM maven:3.8.6-amazoncorretto-17 AS build
COPY src /home/no-loose-coins/src
COPY pom.xml /home/no-loose-coins
RUN mvn -f /home/no-loose-coins/pom.xml clean package -DskipTests


FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY --from=build /home/app/target/*.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Xmx512m","-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${PORT}","-Dspring.profiles.active=prod","-jar", "/app.jar" ]