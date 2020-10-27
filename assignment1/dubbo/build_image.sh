#!/bin/bash
cd go-server;
docker build -t sc2020/goserver .
cd ..
cd go-client
docker build -t sc2020/goclient .
cd ..

cd ./scala-client
docker build -t sc2020/scalaclient .
cd ..
