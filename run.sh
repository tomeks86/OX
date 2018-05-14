#!/bin/bash

PROJECT=OX
VERSION=1.2.0-SNAPSHOT

if [ ! -e target/${PROJECT}-${VERSION}.jar ]; then
	mvn clean install
fi

java -jar target/${PROJECT}-${VERSION}.jar src/main/resources/edu/tseidler/settings.properties
