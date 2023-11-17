#!/bin/bash

source .env

function run_container {
    local image_name=$1
    local sql_file=$2
    local db_name=$3
    local port=$4
    local container_name=$5


    podman build -t $image_name --build-arg SQL_FILE=$sql_file .
    podman run -d --name $container_name -p $port:5432 \
        -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres \
        $image_name
}

function build_and_run_project {
    local database=$1
    local datasource=$2
    local port=$3
    local service_port=$4
    local management_port=$5
    local project_name=$6

    cd "$BASE_DIR/.." || exit
    cd "$project_name" || exit

    export POSTGRESQL_DATABASE=$database
    export POSTGRESQL_SERVICE_HOST=localhost
    export POSTGRESQL_USER=postgres
    export POSTGRESQL_PASSWORD=postgres
    export POSTGRESQL_DATASOURCE=$datasource
    export POSTGRESQL_SERVICE_PORT=$service_port
    export MANAGEMENT_PORT=$management_port

    mvn clean package && wait
    check_error "Error building $project_name project"

    echo "------------------------------------------"
    echo "Finished building $project_name project"
    echo "------------------------------------------"

    echo "------------------------------------------"
    echo "Starting $project_name project on port $port"
    echo "------------------------------------------"

    java -jar target/starter-bootable.jar -Djboss.http.port=$port -Djboss.management.http.port=$management_port &
}

BASE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Remove and run PostgreSQL containers
run_container "ftse/film-postgres:15" $FILM_DB_SQL $FILM_DB_NAME $FILM_DB_PORT "film-db"
run_container "ftse/store-postgres:15" $STORE_DB_SQL $STORE_DB_NAME $STORE_DB_PORT "store-db"
run_container "ftse/customer-postgres:15" $CUSTOMER_DB_SQL $CUSTOMER_DB_NAME $CUSTOMER_DB_PORT "customer-db"

# Build and run projects
build_and_run_project "dvdrentalfilm" "FilmDBDS" 8081 54321 9990 "dvdrental-film"
build_and_run_project "dvdrentalstore" "StoreDBDS" 8082 54322 9991 "dvdrental-store"
build_and_run_project "dvdrentalcustomer" "CustomerDBDS" 8083 54323 9992 "dvdrental-customer"

wait
