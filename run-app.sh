#!/bin/bash

export POSTGRESQL_DATABASE=film-db
export POSTGRESQL_SERVICE_HOST=localhost
export POSTGRESQL_SERVICE_PORT=54321
export POSTGRESQL_USER=postgres
export POSTGRESQL_PASSWORD=postgres
export POSTGRESQL_DATASOURCE=FilmDBDS

mvn clean package && mvn wildfly-jar:run
