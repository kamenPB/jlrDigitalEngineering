FROM openjdk:8
EXPOSE 8080
ADD target/exercise-0.0.1-SNAPSHOT.jar exercise-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/exercise-0.0.1-SNAPSHOT.jar"]