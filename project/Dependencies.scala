import sbt._

object Dependencies {

  val `pekko-serialization` = "com.evolution" %% "pekko-serialization" % "0.0.1"
  val nel = "com.evolutiongaming" %% "nel" % "1.3.4"
  val `metric-tools` = "com.evolutiongaming" %% "metric-tools" % "3.0.0"
  val `cats-helper` = "com.evolutiongaming" %% "cats-helper" % "3.12.0"
  val scache = "com.evolution" %% "scache" % "5.1.2"
  val scalatest = "org.scalatest" %% "scalatest" % "3.2.19"

  object Pekko {
    private val version = "1.1.3"
    val Actor = "org.apache.pekko" %% "pekko-actor" % version
    val ClusterTools = "org.apache.pekko" %% "pekko-cluster-tools" % version
    val Testkit = "org.apache.pekko" %% "pekko-testkit" % version
    val Stream = "org.apache.pekko" %% "pekko-stream" % version
  }

  object Scodec {
    object Scala2 {
      val core = "org.scodec" %% "scodec-core" % "1.11.11" // the last scodec-core version built for 2.13
      val bits = "org.scodec" %% "scodec-bits" % "1.1.38" // scodec-core 1.11.10 is built against 1.1.x
    }

    object Scala3 {
      val core = "org.scodec" %% "scodec-core" % "2.3.2"
      val bits = "org.scodec" %% "scodec-bits" % "1.2.1"
    }
  }

  object Cats {
    val core = "org.typelevel" %% "cats-core" % "2.13.0"
    val effect = "org.typelevel" %% "cats-effect" % "3.5.7"
  }
}
