FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
ARG APP_NAME="mediaplayer-backend-app"
ARG APP_VERSION="0.0.1"
ARG JAR_FILE="/build/libs/${APP_NAME}-${APP_VERSION}.jar"


COPY ${JAR_FILE} app.jar
ENTERPOINT ["java","-jar","app.jar"]