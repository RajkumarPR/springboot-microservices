FROM openjdk:8-jre-alpine
ARG  JAR_FILE=target/*.jar
COPY ${JAR_FILE} cloud-gateway.jar
ENTRYPOINT ["java", "-jar", "/cloud-gateway.jar"]
EXPOSE 9191