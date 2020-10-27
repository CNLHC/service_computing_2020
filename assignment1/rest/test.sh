#!/bin/bash

echo GetName
curl -XGET localhost:4567/person/name

echo SetName To CNLHC
curl -XPOST localhost:4567/person/name\?name=CNLHC
echo GetName
curl -XGET localhost:4567/person/name

echo GetAge
curl -XGET localhost:4567/person/age
echo SetAge to 37
curl -XPOST localhost:4567/person/age\?age=37
echo GetAge
curl -XGET localhost:4567/person/age

echo SayHello
curl -XGET localhost:4567/person/sayHello

