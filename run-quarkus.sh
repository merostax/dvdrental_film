source .env


DOCKERFILE_DB_PATH="./docker/db/Dockerfile"

if podman pod exists $PODNAME; then
    echo "Pod $PODNAME already exists. Skipping pod creation."
else
    podman pod create --name $PODNAME  -p 54321:54321 -p 54322:54322 -p 54323:54323
fi

wait_for_postgres() {
    until podman exec -it $DB_CONTAINER_ID psql -p 54321 -U postgres -d dvdrentalfilm -c '\q' &> /dev/null; do
        echo "Waiting for PostgreSQL to be ready..."
        sleep 5
    done
}


podman build -t $FILM_CONTAINER_NAME_POSTGRES --build-arg SQL_FILE=$FILM_DB_SQL -f $DOCKERFILE_DB_PATH .
DB_CONTAINER_ID=$(podman run -d --pod  $PODNAME  $FILM_CONTAINER_NAME_POSTGRES)


mvn clean package -Pnative -f pom-quarkus.xml
 sleep 5
./target/starter-1.0-runner -J-Dstore.service.uri=http://localhost:8082/ -J-Dcustomer.service.uri=http://localhost:8083/ -J-Dfilm.service.uri=http://localhost:8081/
