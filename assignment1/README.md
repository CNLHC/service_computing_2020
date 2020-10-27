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

基于`Dubbo`框架, 使用`golang`编写服务端, 使用`golang`和`Scala`分别编写客户端.

复现指南:

1. 安装`Docker`
2. 运行[./dubbo/build_image.sh](./dubbo/build_image.sh), 该脚本会构建三个镜像
    1. sc2020/goserver
    2. sc2020/goclient
    3. sc2020/scalaclient

3. 启动本地`zookeeper`注册中心
    ```
    docker run --name dubbo-zookeeper --network host --restart always -d zookeeper
    ```
4. 运行Dubbo服务程序
    ```
    docker run --network host --rm sc2020/goserver
    ```
5. 运行Dubbo测试程序(注: 可以通过修改客户端目录下的测试脚本测试不同Case)
    ```
    docker run --network host --rm sc2020/goclient
    docker run --network host --rm sc2020/scalaclient
    ```

## RESTful 实现

[代码](./rest)

基于`Spring Boot`框架，使用`Scala`语言编写。

复现指南

1. 安装 `sbt`
2. 运行
```
cd rest;
sbt;
sbt> run;
```

3. 测试

```
bash ./test.sh
```

## Web服务实现


[代码](./axis)

基于 `Apache Axis`框架，使用`Java`语言编写. 生成的`wsdl`文件位于[./axis/PersonServices.wsdl](./axis/PersonServices.wsdl)

复现指南

1. 构建镜像
```
cd axis;
#下载axis2文件
wget https://cnpublicstatic.oss-cn-beijing.aliyuncs.com/sc2020/axis2.war ;
docker build -t sc2020/axis .
```

2. 运行镜像，使用`Apache Tomcat`托管Web服务
```
docker run --rm --network host sc2020/axis
```

3. 测试
```
cd axis
./test.sh
```


