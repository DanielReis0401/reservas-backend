#!/bin/bash

cd "$(dirname "$0")" || exit
java -XX:+UseG1GC \
    -Xms512m \
    -Xmx1024m \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 \
    -jar target/reservas-backend-*.jar \
    --systemproperties src/main/resources/app.properties \
    --nocluster \
    --port 8080
