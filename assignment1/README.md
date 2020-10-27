# 服务计算第一次作业
## 题目描述

```
作业 1: 服务开发和调用
Class person
{
    String name;
    int age;
    boolean gender;
    set/getName();
    set/getAge();
    set/getGender();
    String sayHello()
    {
    Return Hello world!+name;
    }
}
1.1 基于 Apache Dubbo 将上述类的方法对外提供 RPC 服务并调用；
1.2 基于 Java Spring 将上述类的方法对外提供 RESTful 服务并调用；
1.3 基于 Apache Axis2 将上述类的方法对外提供 Web 服务，生成 WSDL 文件，以及调用
服务。
```

## RPC 实现

[代码](./dubbo)

基于`Dubbo`框架实现. 使用golang编写服务端，使用golang和scala分别编写客户端.

复现指南:

1. 安装`Docker`
2. 运行[./dubbo/build_image.sh](./dubbo/build_image.sh), 该脚本会构建三个镜像
    1. sc2020/goserver
    2. sc2020/goclient
    3. sc2020/scalaclient

3. 启动一个`zookeeper`做注册中心
    ```
    docker run --name dubbo-zookeeper --network host --restart always -d zookeeper
    ```
4. 运行Dubbo服务程序
    ```
    docker run --network host --rm sc2020/goserver
    ```
5. 运行Dubbo测试程序
    ```
    docker run --network host --rm sc2020/goclient
    docker run --network host --rm sc2020/scalaclient
    ```

## RESTful 实现

[代码](./rest)

基于`Spring Boot`框架，使用Scala语言编写。

复现指南

1. 安装 `sbt`
2. 运行
```
cd rest
sbt
run

```
3. 测试

```
bash ./test.sh
```

## Axis实现

基于 `Axis`框架，使用Java语言编写.

复现指南

1. 构建镜像
```
cd axis
docker build -t sc2020/axis .
```

2. 运行镜像
```
docker run --rm --network host sc2020/axis
```

3. 测试
```
cd axis
./test.sh
```


