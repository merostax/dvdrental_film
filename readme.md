# Einfaches Projekt zum Starten mit WildFly's bootable Jar

## Bauen
mvn clean package

## Betreiben
java -jar target/starter-bootable.jar

## Betreiben im Entwicklungsmodus
mvn wildfly-jar:dev-watch

## To kill ports in case the servers are not properly shut down, use the script kill_ports
./kill_ports

## to start the container and the servers 
./run-bootable.sh

## to clean all  the container and the servers 
