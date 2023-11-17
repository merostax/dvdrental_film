FROM postgres:15

RUN localedef -i de_DE -c -f UTF-8 -A /usr/share/locale/locale.alias de_DE.UTF-8
ENV LANG de_DE.utf-8

ARG SQL_FILE

RUN apt-get update && apt-get install -y wget
RUN wget $SQL_FILE -O /docker-entrypoint-initdb.d/init.sql


