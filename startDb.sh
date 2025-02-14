#!/bin/bash

cd "$(dirname "$0")" || exit
docker compose -f mysql.yaml up
