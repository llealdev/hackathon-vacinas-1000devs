FROM openjdk:17-jdk-slim AS build

WORKDIR /app

RUN apt-get update && apt-get install -y maven ca-certificates

COPY pom.xml /app/
RUN mvn dependency:go-offline  # Baixa dependências antes de copiar os fontes

COPY src /app/src

RUN mvn clean package -DskipTests -U

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 3051

CMD ["java", "-jar", "/app/app.jar", "--server.port=3051"]
































