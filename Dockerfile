FROM docker.io/library/eclipse-temurin:21.0.1_12-jdk-ubi9-minimal

ENV POSTGRESQL_VERSION 42.6.0
ENV POSTGRESQL_DRIVER_URL https://jdbc.postgresql.org/download/postgresql-${POSTGRESQL_VERSION}.jar
ENV POSTGRESQL_DRIVER_PATH /usr/local/lib/postgresql-${POSTGRESQL_VERSION}.jar

RUN curl -L -o ${POSTGRESQL_DRIVER_PATH} ${POSTGRESQL_DRIVER_URL}

COPY target/*.jar starter.jar

ENTRYPOINT ["java", "-jar","starter.jar"]
