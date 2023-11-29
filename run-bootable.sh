#!/usr/bin/bash
source .env

podman stop --all
podman rm --all

podman pod stop --all
podman pod rm --all

podman rmi -f --all

DOCKERFILE_DB_PATH="./docker/db/Dockerfile"
DOCKERFILE_PATH="./docker/app/Dockerfile"

 podman pod create  --name $PODNAME_FILM_DB  -p 54321:54321 -p 8081:8080


podman build -t $FILM_CONTAINER_NAME_POSTGRES --build-arg SQL_FILE=$FILM_DB_SQL -f $DOCKERFILE_DB_PATH .
 podman run  -d  --network podman --pod $PODNAME_FILM_DB   $FILM_CONTAINER_NAME_POSTGRES

podman build -t $FILM_CONTAINER_NAME -f $DOCKERFILE_PATH .
podman run  -t  --network podman --pod $PODNAME_FILM_DB --name $FILM_CONTAINER_NAME  -e POSTGRESQL_USER=postgres  -e POSTGRESQL_PASSWORD=postgres $FILM_CONTAINER_NAME
