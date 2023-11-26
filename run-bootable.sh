#!/bin/bash
source .env

function build_and_run_project {
    local database=$1
    local datasource=$2
    local dbport=$3
    local service_port=$4
    local project_name=$5
    local container_name=$6
    local wildflyport=$7

    cd "$BASE_DIR/.." || exit
    cd "$project_name" || exit

    podman build -t $container_name --build-arg JBOSS_HTTP_PORT=$wildflyport --build-arg JBOSS_MANAGEMENT_HTTP_PORT=$service_port ./docker/backend

    podman run -t --name $container_name  --pod dvdrentalpod --net=host \
        -e POSTGRESQL_DATABASE="$database" \
        -e POSTGRESQL_USER=postgres \
        -e POSTGRESQL_PASSWORD=postgres \
        -e POSTGRESQL_DATASOURCE="$datasource" \
        -e POSTGRESQL_SERVICE_PORT="$dbport" \
        $container_name

}

BASE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# Remove and run PostgreSQL containers

# Build and run projects
build_and_run_project "$FILM_DB_NAME" "$FILM_DATASOURCE" "$FILM_DB_PORT"  "$FILM_WILDFLY_MANAGEMENT_PORT" "dvdrental-film" "$FILM_CONTAINER_NAME" "$FILM_PORT_WILDFLY"
#build_and_run_project "$STORE_DB_NAME" "$STORE_DATASOURCE" "$STORE_DB_PORT"  "$STORE_WILDFLY_MANAGEMENT_PORT" "dvdrental-store" "$STORE_CONTAINER_NAME" "$STORE_PORT_WILDFLY"
#build_and_run_project "$CUSTOMER_DB_NAME" "$CUSTOMER_DATASOURCE" "$CUSTOMER_DB_PORT"  "$CUSTOMER_WILDFLY_MANAGEMENT_PORT""dvdrental-customer" "$CUSTOMER_CONTAINER_NAME" "$CUSTOMER_PORT_WILDFLY"


