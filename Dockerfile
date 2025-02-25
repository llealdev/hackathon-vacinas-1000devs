FROM openjdk:17-jdk-slim AS build

WORKDIR /app

RUN apt-get update && apt-get install -y maven ca-certificates
COPY pom.xml /app/

RUN mvn clean package -DskipTests -U

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/original-vacinas-java-1.0.jar /app/app.jar

EXPOSE 3051

CMD ["java", "-jar", "/app/app.jar"]

































