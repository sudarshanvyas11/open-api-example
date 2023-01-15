FROM eclipse-temurin:17
RUN addgroup -S sudarshan && adduser -S sudarshan -G sudarshan
USER sudarshan:sudarshan
VOLUME /tmp
EXPOSE 8085
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=mysql","/home/sudarshan/projects/open-api/open-api-example.jar"]