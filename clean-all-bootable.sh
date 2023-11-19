#!/bin/bash

podman stop --all
podman rm --all

podman pod stop --all
podman pod rm --all

podman rmi -f --all
