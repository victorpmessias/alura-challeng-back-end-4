FROM openjdk:17-jdk-slim
RUN mvn install jar:jar \
    && cp -a target/*.jar app.jar \
    && rm -rf target "$HOME/.m2"
RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Xmx512m","-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${PORT}","-Dspring.profiles.active=prod","-jar", "/app.jar" ]