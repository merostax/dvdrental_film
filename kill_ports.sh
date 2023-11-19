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
