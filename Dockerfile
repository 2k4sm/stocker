FROM openjdk:17-jdk-slim AS build

COPY . .

RUN ./mvnw clean install -DskipTests

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/stocker-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

CMD ["java","-jar","app.jar"]