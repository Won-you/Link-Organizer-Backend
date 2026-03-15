# 1. 빌드 단계
FROM eclipse-temurin:17-jdk-alpine AS build
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# 2. 실행 단계
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build build/libs/*-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]