FROM openjdk:17
WORKDIR /app
ADD target/hrms-0.0.1-SNAPSHOT.jar hrms-0.0.1-SNAPSHOT.jar 
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=docker", "hrms-0.0.1-SNAPSHOT.jar"]