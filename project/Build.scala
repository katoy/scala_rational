import sbt._
import sbt.Keys._
import xerial.sbt.Pack._

object Build extends sbt.Build {

  lazy val root = Project(
    id = "scala-rational",
    base = file("."),

    settings = Defaults.defaultSettings ++ packSettings ++
      Seq(
        scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
        // Mapping from program name -> Main class
        packMain := Map("rational" -> "rational.Main"),
        // custom settings here
        scalaVersion := "2.10.0",
        crossPaths := false,
        libraryDependencies ++= Seq(
          // Add dependent jars here
          "org.xerial" % "xerial-core" % "3.1",
          "junit" % "junit" % "latest.integration" % "test",
          "com.novocode" % "junit-interface" % "0.10-M2" % "test"
        ),
	testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
      )
  )
}
