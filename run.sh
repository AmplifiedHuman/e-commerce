#!/bin/bash

mvn clean package -Pprod
mkdir -p target/dependency && (cd target/dependency || exit; jar -xf ../*.jar)
docker-compose build && docker-compose up