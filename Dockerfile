FROM eclipse-temurin:21

RUN mkdir /opt/app

COPY target/naves-0.0.1-SNAPSHOT.jar /opt/app

EXPOSE 8080

CMD ["java", "-jar", "/opt/app/naves-0.0.1-SNAPSHOT.jar"]