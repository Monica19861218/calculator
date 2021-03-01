FROM openjdk:16-jdk-alpine

COPY *.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]