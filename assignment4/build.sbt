ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "sc.buaa"

val version = "1.5.5"
lazy val client = (project in file("."))
  .settings(
    name := "analysis",

    libraryDependencies ++= Seq(
      "com.ibm.wala" % "com.ibm.wala.util" % version,
      "com.ibm.wala" % "com.ibm.wala.util" % "1.5.5",
      "com.ibm.wala" % "com.ibm.wala.shrike" % "1.5.5",
      "com.ibm.wala" % "com.ibm.wala.scandroid" % "1.5.5",
      "com.ibm.wala" % "com.ibm.wala.dalvik" % "1.5.5",
      "com.ibm.wala" % "com.ibm.wala.core" % "1.5.5",
      "com.ibm.wala" % "com.ibm.wala.cast.js.rhino" % "1.5.5",
      "com.ibm.wala" % "com.ibm.wala.cast.js" % "1.5.5",
      "com.ibm.wala" % "com.ibm.wala.cast.java.ecj" % "1.5.5",
      "com.ibm.wala" % "com.ibm.wala.cast.java" % "1.5.5",
      "com.ibm.wala" % "com.ibm.wala.cast" % "1.5.5",
      "com.github.scopt" %% "scopt" % "4.0.0",
    ),
  )



