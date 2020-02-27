
name := "hello-stey-4l"
scalaVersion in ThisBuild := "2.13.1"
organization in ThisBuild := "com.stey"

version in ThisBuild := "1.0.0"

ThisBuild / scalacOptions ++= Seq("-feature", "-language:postfixOps", "-language:implicitConversions")

lazy val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"

lazy val `stey` = project in file(".")

lazy val `hello-api` = (project in file("hello-api"))
  .settings(
    name := "hello-api",
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hello-impl` = (project in file("hello-impl"))
  .enablePlugins(LagomScala)
  .settings(
    name := "hello-impl",
    libraryDependencies ++= Seq(
      macwire
    )
  )
  .dependsOn(`hello-api`)