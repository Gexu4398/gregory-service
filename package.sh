#!/bin/bash

docker pull maven:3.9.11-eclipse-temurin-21
docker pull eclipse-temurin:21-jre-alpine
docker pull postgres:alpine3.22
docker pull keycloak/keycloak:26.3.2

TAG=`date +%Y%m%d%H%M%S`

docker run -it --rm --name build -v $(pwd):/usr/src/mymaven -v ~/.m2:/root/.m2 -w /usr/src/mymaven maven:3.9.11-eclipse-temurin-21 mvn -DskipTests=true clean install
docker build . --build-arg JAR_FILE=./biz-service/target/biz-service.jar -t gregoryservice/bizservice:$TAG

mkdir -p deploy/images

rm ./deploy/images/gregoryservice_bizservice_*

docker save -o ./deploy/images/gregoryservice_bizservice_$TAG.tar gregoryservice/bizservice:$TAG

