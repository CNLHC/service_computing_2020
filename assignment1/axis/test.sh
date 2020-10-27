
#!/bin/bash

echo GetName
curl -XGET http://localhost:8888/axis2/services/PersonServices/getName
echo ''


echo SetName To DongTian
curl -XGET http://localhost:8888/axis2/services/PersonServices/setName\?rname=DongTian
echo ''

echo GetName
curl -XGET http://localhost:8888/axis2/services/PersonServices/getName
echo ''

echo GetAge
curl -XGET http://localhost:8888/axis2/services/PersonServices/getAge
echo ''

echo SetAge to 42
curl -XGET http://localhost:8888/axis2/services/PersonServices/setAge\?rage=42
echo ''

echo GetAge
curl -XGET http://localhost:8888/axis2/services/PersonServices/getAge
echo ''

echo SayHello
curl -XGET http://localhost:8888/axis2/services/PersonServices/sayHello
echo ''

