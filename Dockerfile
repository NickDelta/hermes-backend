FROM adoptopenjdk/openjdk11:alpine
COPY target/hermes-backend.jar ./backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]