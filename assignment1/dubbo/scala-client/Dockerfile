from openjdk:8
WORKDIR /java
RUN wget https://cnpublicstatic.oss-cn-beijing.aliyuncs.com/sc2020/scala-client.jar
COPY ./test.sh .
ENTRYPOINT ./test.sh
