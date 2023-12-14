#!/usr/bin/bash
##README lesen

source .env
mvn clean  package
wait $!
DOCKERFILE_DB_PATH="./docker/db/Dockerfile"
DOCKERFILE_PATH="./docker/app/Dockerfile"

if podman pod exists $PODNAME; then
    echo "Pod $PODNAME already exists. Skipping pod creation."
else
    podman pod create --name $PODNAME  -p 54321:54321 -p 54322:54322 -p 54323:54323 -p 8083:8083 -p 8082:8082 -p 8081:8081
fi

wait_for_postgres() {
    until podman exec -it $DB_CONTAINER_ID psql -p 54321 -U postgres -d dvdrentalfilm -c '\q' &> /dev/null; do
        echo "Waiting for PostgreSQL to be ready..."
        sleep 5
    done
}

# Build and run the PostgreSQL container
podman build -t $FILM_CONTAINER_NAME_POSTGRES --build-arg SQL_FILE=$FILM_DB_SQL -f $DOCKERFILE_DB_PATH .
DB_CONTAINER_ID=$(podman run -d --pod  $PODNAME  $FILM_CONTAINER_NAME_POSTGRES)

wait_for_postgres

# Build and run the main container
podman build -t $FILM_CONTAINER_NAME -f $DOCKERFILE_PATH .
podman run -d --pod $PODNAME -e POSTGRESQL_SERVICE_HOST=localhost -e POSTGRESQL_SERVICE_PORT=54321 -e POSTGRESQL_DB=dvdrentalfilm -e POSTGRESQL_USER=postgres -e POSTGRESQL_PASSWORD=trust $FILM_CONTAINER_NAME
