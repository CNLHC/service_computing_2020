ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "sc.buaa"
import scala.sys.process._
import sbt._, Keys._


val AXIS_HOME ="./unmanaged/axis2-1.7.9"
val JAVA_HOME = "/usr/lib/jvm/java-8-openjdk-amd64"

lazy val java2wsdl = taskKey[Unit]("generate wsdl")
lazy val deploy_aar = taskKey[Unit]("deploy aar")

lazy val commonSettings = Seq(
    artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
      "axis_"+artifact.name + "." + "aar"
    },
)

val  Services = Seq( "BasicInfo","FundamentalAnalysis","HistoryData","TechnicalAnalysis")


lazy val SvcBasicInfo = (project in file("./services/BasicInfo"))
            .settings(
            name:="BasicInfoService",
            commonSettings,
            )
lazy val SvcFundamentalAnalysis= (project in file("./services/FundamentalAnalysis"))
            .settings( name:="FundamentalAnalysisService",
            commonSettings,
            )
lazy val SvcTechnicalAnalysis = (project in file("./services/TechnicalAnalysis"))
            .settings( name:="TechnicalAnalysisService",
            commonSettings,
            )
lazy val SvcHistoryData = (project in file("./services/HistoryData"))
            .settings( name:="HistoryDataService",

            commonSettings,
            )


lazy val client = (project in file("."))
  .settings(
    name := "Assignment3",
    exportJars:= true,
    compileOrder := CompileOrder.JavaThenScala,
    Compile / unmanagedClasspath += baseDirectory.value/"unmanaged/axis2-1.7.9/lib",
    libraryDependencies += "org.apache.axis" % "axis" % "1.4",
    java2wsdl :={
        Process("mkdir -pf ./wsdl")!
        val prefix = "sc.buaa.assignment3"
        val service = List( "TechnicalAnalysis", "GetSymbolContext", "GetAverageData", "GetHistoryData")
        service.foreach((e)=>{
            Process(s"${AXIS_HOME}/bin/java2wsdl.sh"
            :: "-o ./wsdl"
            ::s"-of ${e}.wsdl" 
            ::s"-cn ${prefix}.${e}"
            :: "-cp ./target/scala-2.12/classes/"
            ::Nil
            ,file(".")
            ,"JAVA_HOME" -> JAVA_HOME)!
        })
    },
    deploy_aar:={
       import Path._
       Services.foreach(e=>{
           val base = (baseDirectory.value/s"services/${e}/target/scala-2.12/") 
           val artifacts = base * "*.aar" 
           val dst  = file("./unmanaged/apache-tomcat-5.5.26/webapps/ode/WEB-INF/services")
           val pair = artifacts.get() pair rebase(base,dst)
           IO.copy(pair,CopyOptions.apply())
        })
    }
  )
  .aggregate(
    SvcHistoryData,
    SvcTechnicalAnalysis,
    SvcFundamentalAnalysis,
    SvcBasicInfo
  )
