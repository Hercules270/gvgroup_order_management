FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY build/libs/*.jar order-management-1.0.0.jar
ENTRYPOINT ["java", "-jar", "order-management-1.0.0.jar"]