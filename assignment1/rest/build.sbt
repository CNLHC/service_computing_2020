
import slick.codegen.SourceCodeGenerator
import slick.{ model => m }

lazy val restful = (project in file("."))
	.enablePlugins(CodegenPlugin)
    .settings(
        name:="person_restful",
        libraryDependencies ++= Seq(

        "org.springframework.boot" % "spring-boot-starter-web" % "2.3.4.RELEASE",
        "com.fasterxml.jackson.core"%"jackson-core"%"2.8.7",
        "com.fasterxml.jackson.core"%"jackson-annotations"%"2.8.7",
        "com.fasterxml.jackson.core"%"jackson-annotations"%"2.8.7",
        "org.json4s" %% "json4s-jackson" % "3.6.9",
        "org.apache.tomcat.embed" % "tomcat-embed-core"         % "7.0.53" % "container",
        "org.apache.tomcat.embed" % "tomcat-embed-logging-juli" % "7.0.53" % "container",
        "org.apache.tomcat.embed" % "tomcat-embed-jasper"       % "7.0.53" % "container",
        "com.typesafe.slick" %% "slick" % "3.3.3",
        "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",
		"org.xerial" % "sqlite-jdbc" % "3.32.3.2"
        )
    ).settings(
		slickCodegenDatabaseUrl := "jdbc:sqlite:../person.db",
		slickCodegenDriver := slick.jdbc.SQLiteProfile,
     	slickCodegenJdbcDriver := "org.sqlite.JDBC",
		slickCodegenOutputPackage := "sc.buaa.assign1.restful",
		//sourceGenerators in Compile += slickCodegen.taskValue,
		slickCodegenOutputDir := baseDirectory.value / "src"/"main"/"scala",
		sourceGenerators in Compile += slickCodegen.taskValue
	)




//important
//we need to spawn tomcat into new thread or we can not rerun our program.
fork in run:= true


parallelExecution in run:= false

