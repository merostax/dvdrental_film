#!/bin/bash
source .env
podman pod rm --force $PODNAME

