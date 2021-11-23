FROM openjdk:16-jdk-slim
ARG JAR_FILE=book-service/target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-DJava.security.egd=file:/dev/./urandom","-jar","/app.jar"]