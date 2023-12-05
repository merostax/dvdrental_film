FROM docker.io/library/postgres:15

ARG SQL_FILE

RUN localedef -i de_DE -c -f UTF-8 -A /usr/share/locale/locale.alias de_DE.UTF-8
ENV LANG de_DE.utf-8
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=trust
ENV POSTGRES_PORT 54321

RUN apt-get update && apt-get install -y wget

RUN wget $SQL_FILE -O /docker-entrypoint-initdb.d/init.sql

RUN echo "port = ${POSTGRES_PORT}" >> /usr/share/postgresql/postgresql.conf.sample
EXPOSE 54321
CMD ["postgres", "-c", "config_file=/usr/share/postgresql/postgresql.conf.sample"]



