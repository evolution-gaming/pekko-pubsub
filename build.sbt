import Dependencies.*

name := "pekko-pubsub"

organization := "com.evolution"

homepage := Some(url("https://github.com/evolution-gaming/pekko-pubsub"))

startYear := Some(2017)

organizationName := "Evolution"

organizationHomepage := Some(url("https://evolution.com"))

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.16", "3.3.6")

publishTo := Some(Resolver.evolutionReleases)

libraryDependencies ++= Seq(
  Pekko.Actor,
  Pekko.Stream,
  Pekko.ClusterTools,
  Pekko.Testkit % Test,
  Cats.core,
  Cats.effect,
  `metric-tools`,
  `pekko-serialization`,
  `cats-helper`,
  scache,
  scalatest % Test,
)

//see https://github.com/scodec/scodec/issues/365
libraryDependencies ++= crossSettings(
  scalaVersion = scalaVersion.value,
  if2 = Seq(
    Scodec.Scala2.core,
    Scodec.Scala2.bits,
  ),
  if3 = Seq(
    Scodec.Scala3.core,
    Scodec.Scala3.bits,
  ),
)

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

scalacOptions ++= crossSettings(
  scalaVersion = scalaVersion.value,
  // Good compiler options for Scala 2.13 are coming from com.evolution:sbt-scalac-opts-plugin:0.0.9,
  // but its support for Scala 3 is limited, especially what concerns linting options.
  //
  // If Scala 3 is made the primary target, good linting scalac options for it should be added first.
  if3 = Seq(
    "-Ykind-projector:underscores",

    // disable new brace-less syntax:
    // https://alexn.org/blog/2022/10/24/scala-3-optional-braces/
    "-no-indent",

    // improve error messages:
    "-explain",
    "-explain-types",
  ),
  if2 = Seq(
    "-Xsource:3",
    "-release:17",
    "-deprecation",
  ),
)

Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")

//addCommandAlias("check", "all versionPolicyCheck scalafmtCheckAll scalafmtSbtCheck")
addCommandAlias("check", "+all scalafmtCheckAll scalafmtSbtCheck")
addCommandAlias("fmt", "+all scalafmtAll scalafmtSbt")
addCommandAlias("build", "+all compile test")

def crossSettings[T](scalaVersion: String, if3: T, if2: T): T =
  scalaVersion match {
    case version if version.startsWith("3") => if3
    case _ => if2
  }
