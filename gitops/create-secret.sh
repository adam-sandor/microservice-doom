#!/usr/bin/env bash
set -e

kubectl create secret generic doom-state-secrets \
    --from-literal=doom-engine-password=enginepwd \
    --from-literal=doom-client-password=clientpwd