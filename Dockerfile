FROM eclipse-temurin:8 AS runtime

COPY /web/target/axon-bank-web-0.0.1-SNAPSHOT.jar axon-bank-web-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=distributed", "-jar", "axon-bank-web-0.0.1-SNAPSHOT.jar"]