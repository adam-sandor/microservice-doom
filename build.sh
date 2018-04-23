#!/usr/bin/env bash

set -e

MODULE=$1

VERSION=$2

mvn -f ${MODULE}/pom.xml package

docker build --no-cache -t adamsandor83/${MODULE} ./${MODULE}

docker tag adamsandor83/${MODULE}:latest ${MODULE}:${VERSION}

#docker push adamsandor83/${MODULE}:${VERSION}
