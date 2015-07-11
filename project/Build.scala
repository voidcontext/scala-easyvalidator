package build

import sbt._
import Keys._

object BricksetClientBuild extends Build {
  lazy val root = Project(
    "validator",
    file("."),
    settings = Defaults.defaultSettings ++ Seq(
      name := "validator",
      organization := "io.github.voidcontext",
      version := "0.1.0",
      scalaVersion := "2.11.6",
      scalacOptions := Seq("-feature", "-deprecation"),
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "2.2.5" % "test"
      )
    )
  )

}
