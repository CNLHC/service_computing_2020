# 基于WSDL和BPEL的服务调度

## 背景介绍

[BPEL(Business Process Execution Language)][BPELStandard]是一种基于XML的描述性语言，可以实现对SOA服务的调度。

本次实验中，我使用[Apache Axis](ApacheAxis)创建了4个Web服务，并使用[Apache ODE]对这四个服务进行编排。

## 实验过程

1. 安装eclipse以及BPEL editor插件,(详见[环境安装])
2. 构建简单的web服务,代码参见[./src](./src)
3. 编写BPEL文件和WSDL文件
4. 使用`tomcat`部署服务([服务部署])并测试

## 实验结果

## 结论


## 附录

[BPELStandard]:(http://docs.oasis-open.org/wsbpel/2.0/OS/wsbpel-v2.0-OS.html)
[环境安装]:(./installation.md)
[服务部署]:(./installation.md)
