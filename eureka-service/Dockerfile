FROM maven:4.0.0-rc-7-eclipse-temurin-21-alpine as builder
WORKDIR /app
COPY . .
RUN mvn -pl eureka-service -am package -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.24
WORKDIR /app
COPY --from=builder /app/eureka-service/target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT [ "java", "-jar", "app.jar" ]
