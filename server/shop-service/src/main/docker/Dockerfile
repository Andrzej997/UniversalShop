FROM java:8
VOLUME /tmp
ADD shop-service-1.0.jar app.jar
ADD src/main/resources/truststore.jks truststore.jks
RUN bash -c 'touch /app.jar'
EXPOSE 9000
ENV SPRING_PROFILES_ACTIVE docker
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]