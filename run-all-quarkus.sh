#!/bin/bash

chmod +x run-quarkus.sh
./run-quarkus.sh

cd .. && cd dvdrental-store
chmod +x run-quarkus.sh
./run-quarkus.sh


cd .. && cd dvdrental-customer
chmod +x run-quarkus.sh
./run-quarkus.sh

