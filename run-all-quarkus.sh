#!/bin/bash
##README lesen
run_quarkus() {
  chmod +x run-quarkus.sh
  ./run-quarkus.sh
}

run_quarkus

(cd .. && cd dvdrental-store && run_quarkus)

(cd .. && cd dvdrental-customer && run_quarkus)

## falls error mit jvm thread emfehlbar das zu nutzen
## MAVEN_OPTS="-Xmx512m -Xms256m"
