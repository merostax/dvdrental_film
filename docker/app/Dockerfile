FROM docker.io/library/eclipse-temurin:21.0.1_12-jdk-ubi9-minimal

COPY target/*.jar starter.jar

CMD ["java", "-jar", "-Djboss.bind.address=0.0.0.0", "-Djboss.bind.address.management=0.0.0.0", "-Djboss.http.port=8081", "starter.jar"]

