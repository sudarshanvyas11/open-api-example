FROM openjdk:17
ADD target/open-api-example-1.0-SNAPSHOT.jar open-api-example.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=mysql","open-api-example.jar"]