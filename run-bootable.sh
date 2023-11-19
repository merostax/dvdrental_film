#!/bin/bash

source .env

#!/bin/bash

stop_processes_using_ports() {
  for port in "${PORTS_TO_STOP[@]}"; do
    # Use lsof to find processes using the specified port
    processes="$(lsof -i :$port | awk 'NR>1 {print $2}' | sort -u)"

    if [ -n "$processes" ]; then
      echo "Processes using port $port: $processes"
      for pid in $processes; do
        echo "Killing process $pid using port $port"
        kill "$pid"
        check_error "Error killing process $pid using port $port"
      done
    else
      echo "No processes found using port $port"
    fi
  done
}

PORTS_TO_STOP=( 8081 8082 8083 9990 9993 9991 9992 9994)

stop_processes_using_ports || true

function build_and_run_project {
    local database=$1
    local datasource=$2
    local port=$3
    local service_port=$4
    local management_port=$5
    local project_name=$6
    cd "$BASE_DIR/.." || exit
    cd "$project_name" || exit
     podman build -t dvd .
podman run -t --name dvd --pod dvdrentalpod -p 8081:8081 -e POSTGRESQL_DATABASE=dvdrentalfilm -e POSTGRESQL_USER=postgres -e POSTGRESQL_PASSWORD=postgres -e POSTGRESQL_DATASOURCE=FilmDBDS -e POSTGRESQL_SERVICE_PORT=54321 dvd


}
BASE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# Remove and run PostgreSQL containers

# Build and run projects
build_and_run_project "dvdrentalfilm" "FilmDBDS" 8081 54321 9990 "dvdrental-film"

wait
