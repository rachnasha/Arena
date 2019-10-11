name := "Artist"

val commonSettings = Seq(
  organization := "arena",
  version := "0.1",
  scalaVersion := "2.12.7",
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
)

lazy val artist = project.in(file("."))
  .settings(commonSettings:_*)
  .settings(
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )