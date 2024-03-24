FROM gradle:jdk21 as build
WORKDIR /app
COPY src ./src
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
RUN ./gradlew clean build -x test

FROM gradle:jdk21 as runner
COPY --from=build /app/build/libs/aspect-sample-0.0.1.jar /aspect-sample.jar
ENTRYPOINT ["java","-jar","/aspect-sample.jar"]

# docker build -t sample-aspect .
