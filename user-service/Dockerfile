FROM maven:4.0.0-rc-7-eclipse-temurin-21-alpine as builder
WORKDIR /app
COPY . .
RUN mvn -pl user-service -am package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/user-service/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
