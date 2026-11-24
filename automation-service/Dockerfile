FROM eclipse-temurin:4.0.0-rc-7-eclipse-temurin-21-alpine as builder
WORKDIR /app
COPY . .
RUN mvn -pl automation-service -am package -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.24
WORKDIR /app
COPY --from=builder /app/automation-service/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "app.jar" ]
