FROM adoptopenjdk/openjdk11:alpine
COPY target/hermes-backend.jar app.jar
RUN addgroup -S hermesgroup && adduser -S hermes -G hermesgroup
USER hermes
EXPOSE 8080
CMD java -jar app.jar