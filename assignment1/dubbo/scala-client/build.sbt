ThisBuild / scalaVersion := "2.12.7"
ThisBuild /  organization:= "sc.buaa"

lazy val client = (project in file("."))
    .settings(
        name := "client",
        libraryDependencies ++= Seq(
        "com.alibaba" % "dubbo" % "2.6.5",
        "org.apache.curator" % "curator-framework" % "2.12.0",
        "org.slf4j" % "slf4j-log4j12" % "1.6.0",
        "org.slf4j" % "slf4j-api" % "1.6.2",
        "org.slf4j" % "slf4j-simple" % "1.7.21",
        "com.github.scopt" %% "scopt" % "4.0.0-RC2"
        ),
	).settings(
        assemblyJarName in assembly := "./scala-client.jar",
		assemblyMergeStrategy in assembly := {
        // Fuck this, it takes me almost 10 hours to figure out the correct solution.
	      case PathList("META-INF", xs @ _*) => xs match {
                  case "spring.schemas"::ns =>MergeStrategy.concat
                  case "spring.handlers"::ns => MergeStrategy.concat
                  case "compat"::ns=>MergeStrategy.deduplicate
                  case "dubbo.xsd"::ns => MergeStrategy.deduplicate
                  case "dubbo"::ns => MergeStrategy.deduplicate
                  case _ =>MergeStrategy.discard
              }
		  case x => MergeStrategy.first
		}
    )

