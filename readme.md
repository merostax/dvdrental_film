# Einfaches Projekt zum Starten mit WildFly's bootable Jar

## Bauen
mvn clean package

## Betreiben
java -jar target/starter-bootable.jar

## Betreiben im Entwicklungsmodus
mvn wildfly-jar:dev-watch

## Dockerfile
Die Umgebungsvariablen sind im Dockerfile konfiguriert. Bei Bedarf können Sie auch die Ports ändern, indem Sie die CMD[] Anweisung anpassen.

## Anwendungsproperties
Die PostgreSQL-Variablen sind in der Datei application(Die ist wichtig für QURKUS).properties festgelegt. Sie können sie nach Ihren Wünschen in dieser Datei ändern.

## Ausführen von Bootable mit Container
Für jedes Projekt gibt es ein Skript(run_bootable_mit_container), das aufgerufen wird, wenn alle bootfähigen Projekte einzeln ausgeführt werden. Dieses Skript befindet sich in jedem Projekt und wird als Teil des Gesamtlaufs ausgeführt.

## PostgreSQL-Passwort
Das PostgreSQL-Passwort ist in der Anwendung auf "trust" gesetzt. Sie können das Passwort entsprechend Ihren Sicherheitspräferenzen in der Anwendungskonfiguration ändern.

## SQL-URL

Falls keine Datenbank erstellt wurde, bitte die Internetverbindung zur Ostfalia überprüfen. Eine Überprüfung findet im Skript statt: "Waiting for PostgreSQL to be ready...".
Wenn es nach einer bestimmten Zeit nicht zum nächsten Befehl übergeht, liegt dies möglicherweise an der SQL-Datenbank, die nicht korrekt geladen wird.
 ## URiS
Die URI für den Microservice, zum Beispiel für Filme, sollte bereitgestellt werden. Ebenso sollten Customer- und Store-URIs mitgeliefert werden, falls sie benötigt werden. Der Grund dafür liegt darin, dass in der Anwendung diese URIs je nach Anforderung abgefragt werden, und in diesem Fall ziehe ich es vor, direkt die URI des Films aus dem Filmprojekt zu verwenden.
Es ist möglich, die @context-URI zu nutzen, aber das würde für mich viele Änderungen erfordern. Daher empfehle ich, es so zu belassen.

## Quarkus
falls "disown" nicht zu den nachste uberspringt bitte der letzte quarkus.sh manual ausführen.
