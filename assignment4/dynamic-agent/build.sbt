
import java.util.jar.{Attributes, Manifest}
import sbt.Package.ManifestAttributes


lazy val sc_profiler= (project in file("."))
.settings(
    libraryDependencies ++= Seq(
        "org.javassist" % "javassist" % "3.27.0-GA",
    ),
	packageOptions := Seq(ManifestAttributes(
		"Premain-Class" -> "xyz.cnworkshop.agent.ProfilingAgent",
		"Agent-Class" -> "xyz.cnworkshop.agent.ProfilingAgent",
		"Can-Redefine-Classes" -> "true",
		"Can-Retransform-Classes" -> "true",
		"Can-Set-Native-Method-Prefix" -> "true",
	))
)
.settings(
    assemblyJarName in assembly := "buaa_sc_profiler.jar"
)


