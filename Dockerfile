FROM openjdk:11-jre-slim
COPY target/hermes-backend.jar app.jar
RUN groupadd hermes && useradd -g users -G hermes hermes
USER hermes
EXPOSE 8080
CMD java -jar app.jar