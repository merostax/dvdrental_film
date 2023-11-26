source .env
podman rm -f test3
 podman build -t test3 .
podman run -t --pod $PODNAME --name test3 \
  -e POSTGRESQL_DATABASE="$FILM_DB_NAME" \
  -e POSTGRESQL_USER=postgres \
  -e POSTGRESQL_PASSWORD=postgres \
  -e POSTGRESQL_DATASOURCE=FilmDBDS\
  test3



