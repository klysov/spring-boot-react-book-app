FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM arm64v8/eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]