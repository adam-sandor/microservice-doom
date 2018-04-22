#!/usr/bin/env bash

set -e

MODULE=$1

VERSION=$2

mvn -f ${MODULE}/pom.xml package docker:build

docker tag adamsandor83/${MODULE}:latest adamsandor83/${MODULE}:${VERSION}

docker push adamsandor83/${MODULE}:${VERSION}
