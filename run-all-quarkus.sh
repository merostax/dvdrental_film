#!/bin/bash
##README lesen
chmod +x run-quarkus.sh
./run-quarkus.sh

sleep 10

cd .. && cd dvdrental-store
chmod +x run-quarkus.sh
./run-quarkus.sh

sleep 10

cd .. && cd dvdrental-customer
chmod +x run-quarkus.sh
./run-quarkus.sh

