podman build -t $FILM_CONTAINER_NAME_POSTGRES --build-arg SQL_FILE=$FILM_DB_SQL -f $DOCKERFILE_DB_PATH .
podman run  -d   -p 54321:54321 $FILM_CONTAINER_NAME_POSTGRES


mvn clean  package -Pnative  -f pom-quarkus.xml
./target/starter-1.0-runner
