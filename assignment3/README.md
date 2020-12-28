# 基于WSDL和BPEL的服务调度

## 背景介绍

[BPEL(Business Process Execution Language)][BPELStandard]是一种基于XML的描述性语言，可以实现对SOA服务的调度。

本次实验中，我使用 [Apache Axis] 建了四个Web服务，并使用 [Apache ODE] 对他们进行编排。

## 实验过程

1. 安装`eclipse`以及`BPEL editor`插件,(详见[环境安装])
2. 使用java语言构建简单的web服务,代码参见[./src](./src)
3. 编写`BPEL`文件和`WSDL`文件([bpel](./bpelContent/Login.bpel),[wsdl](./bpelContent/LoginArtifacts.wsdl))

完成后的BPEL在编辑器中的图示
![](https://publicstatic.cnworkshop.xyz/sc2020_doc/bpel/BPELEditor.png)

完成后的WSDL在编辑器中的图示

![](https://publicstatic.cnworkshop.xyz/sc2020_doc/bpel/WSDL.png)

4. 使用`tomcat`部署服务([服务部署])并测试

## 实验结果

使用curl调用部署在`tomcat`中的Web服务。

```bash
curl localhost:4567/ode/processes/BasicInfo/process\?symbol=AAAl
```

接口返回:

```xml
<ProcessResponse xmlns="http://sc.buaa.assignment3" xmlns:tns="http://sc.buaa.assignment3" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <tns:BasicInfo>Mocking BasicInfo: AAAl</tns:BasicInfo>
  <tns:HistoryData>Mocking HistoryData: AAAl</tns:HistoryData>
  <tns:TechnicalAnalysis>Mocking Technical Index: AAAl</tns:TechnicalAnalysis>
  <tns:FundamentalAnalysis>Mocking Report: AAAl</tns:FundamentalAnalysis>
</ProcessResponse>
```

该`xml`文档共有四个字段: `BasicInfo`,`HistoryData`,`TechnicalAnalysis`,`FundamentalAnalysis`, 分别表示个股的基本信息，历史数据，技术指标分析结果，基本面分析结果。请求时将个股代码`AAAl`编码在url中，`AXIS`框架自动将http请求转换为`SOAP`请求，并在`ODE`的调度下按照顺序调用不同服务，将他们的输出组装成最终结果后返回给调用者。

## 结论

以前总是听说用`java`和`soap`这样一套轮子做web开发繁琐。通过这次实验，我对这个观点有了更深刻的认识。





[BPELStandard]:http://docs.oasis-open.org/wsbpel/2.0/OS/wsbpel-v2.0-OS.html
[Apache Axis]:http://axis.apache.org/axis2/java/core/
[Apache ODE]: http://ode.apache.org/
[环境安装]:./installation.md
[服务部署]:./installation.md
