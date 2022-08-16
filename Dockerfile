FROM maven:3.8.6-amazoncorretto-17 AS build
COPY src /home/no-loose-coins/src
COPY pom.xml /home/no-loose-coins
RUN mvn -f /home/no-loose-coins/pom.xml clean package


FROM openjdk:17-jdk-slim
COPY --from=build /home/no-loose-coins/target/*.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Xmx480m","-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${PORT}","-Dspring.profiles.active=prod","-jar", "/app.jar" ]