#!/bin/bash

source .env
podman stop --all
podman rm --all

podman pod stop  --all
podman pod rm --all

podman rmi -f --all


create_pod() {
    local pod_name=$1
    podman pod create --name $pod_name --share cgroup
}

add_container_to_pod() {
    local pod_name=$1
    local image_name=$2
    local sql_file=$3
    local container_name=$5
    local host_port=$6

    podman build -t $image_name --build-arg SQL_FILE=$sql_file  ./docker/db
    podman run -d --name $container_name \
        --pod $pod_name \
        -e POSTGRES_USER=postgres \
        -e POSTGRES_PASSWORD=postgres \
        -p $host_port:5432 \
        $image_name
}

create_pod "dvdrentalpod"
add_container_to_pod "dvdrentalpod" "ftse/film-postgres:15" $FILM_DB_SQL $FILM_DB_NAME "dvdrentalfilm" $FILM_DB_PORT
add_container_to_pod "dvdrentalpod" "ftse/store-postgres:15" $STORE_DB_SQL $STORE_DB_NAME "dvdrentalstore" $STORE_DB_PORT
add_container_to_pod "dvdrentalpod" "ftse/customer-postgres:15" $CUSTOMER_DB_SQL $CUSTOMER_DB_NAME "dvdrentalcustomer" $CUSTOMER_DB_PORT
