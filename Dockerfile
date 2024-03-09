FROM openjdk:17-alpine
WORKDIR /app
COPY target/hello-docker-1.0.0.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","hello-docker-1.0.0.jar"]