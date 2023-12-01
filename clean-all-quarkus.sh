#!/bin/bash

podman stop --all
podman rm -f --all

podman pod stop --all
podman pod rm -f --all

podman rmi -f --all
