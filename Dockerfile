FROM openjdk:16-jdk-alpine

COPY target/*.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]