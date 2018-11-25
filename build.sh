#!/bin/sh

mvn clean package
docker build -t antoineaube/vcs:latest .