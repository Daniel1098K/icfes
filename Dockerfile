# ---- ETAPA 1: BUILD CON MAVEN ----
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# ---- ETAPA 2: IMAGEN FINAL LIGERA ----
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8100

ENTRYPOINT ["java", "-jar", "app.jar"]
