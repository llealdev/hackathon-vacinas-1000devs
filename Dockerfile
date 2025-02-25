FROM openjdk:17-jdk-slim AS build

WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/vacinas-java-1.0.jar /app/app.jar

EXPOSE 3050

CMD ["java", "-jar", "/app/app.jar"]

































