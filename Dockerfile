FROM openjdk:17
ADD target/conway-game-of-life-1.0-SNAPSHOT.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]
