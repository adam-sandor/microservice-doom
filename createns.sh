#!/usr/bin/env bash
set -e

kubectl create ns $1

kubectl create secret generic doom-state-secrets \
    --from-literal=doom-engine-password=enginepwd \
    --from-literal=doom-client-password=clientpwd

echo "Namespace $1 created"
kubens $1
