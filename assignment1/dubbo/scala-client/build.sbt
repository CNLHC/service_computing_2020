ThisBuild / scalaVersion := "2.12.7"
ThisBuild /  organization:= "sc.buaa"

lazy val client = (project in file("."))
    .settings(
        name := "client",
        libraryDependencies += "com.alibaba" % "dubbo-dependencies-bom" % "2.6.5" ,
        libraryDependencies += "com.qianmi" % "dubbo-rpc-jsonrpc" % "1.0.1",
        libraryDependencies += "com.alibaba" % "dubbo" % "2.6.5",
        libraryDependencies += "org.apache.curator" % "curator-framework" % "2.12.0",
        libraryDependencies += "org.javassist" % "javassist" % "3.20.0-GA",
        libraryDependencies += "org.jboss.netty" % "netty" % "3.2.5.Final",
        libraryDependencies += "org.apache.mina" % "mina-core" % "1.1.7",
        libraryDependencies += "org.glassfish.grizzly" % "grizzly-core" % "2.1.4",
        libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.7",
        libraryDependencies += "com.alibaba" % "fastjson" % "1.2.56",
        libraryDependencies += "com.thoughtworks.xstream" % "xstream" % "1.4.7",
        libraryDependencies += "org.apache.bsf" % "bsf-api" % "3.1",
        libraryDependencies += "org.apache.zookeeper" % "zookeeper" % "3.4.14",
        libraryDependencies += "com.github.sgroschupf" % "zkclient" % "0.1",
        libraryDependencies += "com.netflix.curator" % "curator-framework" % "1.1.16",
        libraryDependencies += "com.googlecode.xmemcached" % "xmemcached" % "1.3.6",
        libraryDependencies += "org.apache.cxf" % "cxf-rt-frontend-simple" % "2.6.1",
        libraryDependencies += "org.apache.cxf" % "cxf-rt-transports-http" % "2.6.1",
        libraryDependencies += "org.apache.thrift" % "libthrift" % "0.12.0",
        libraryDependencies += "com.caucho" % "hessian" % "4.0.7",
        libraryDependencies += "javax.servlet" % "servlet-api" % "2.5",
        libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.26",
        libraryDependencies += "log4j" % "log4j" % "1.2.16",
        libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.2",
        libraryDependencies += "redis.clients" % "jedis" % "2.1.0",
        libraryDependencies += "javax.validation" % "validation-api" % "1.0.0.GA",
        libraryDependencies += "org.hibernate" % "hibernate-validator" % "4.2.0.Final",
        libraryDependencies += "javax.cache" % "cache-api" % "0.4",
        libraryDependencies += "com.github.scopt" %% "scopt" % "4.0.0-RC2"
	)
