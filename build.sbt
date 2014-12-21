name := "avro-scala-runtime-typeprovider"

version := "0.0.2"

organization := "com.julianpeeters"

scalaVersion := "2.10.4"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq( 
  "com.julianpeeters" %% "case-class-generator" % "0.6.1",
  "org.apache.avro" % "avro" % "1.7.7",
  "org.slf4j" % "slf4j-simple" % "1.7.5", 
  "org.specs2" %% "specs2" % "2.4" % "test",
  "com.novus" %% "salat" % "1.9.9" % "test"
)

publishMavenStyle := true

publishArtifact in Test := false

publishTo <<= version { (v: String) =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT"))
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
    }

pomIncludeRepository := { _ => false }

licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

homepage := Some(url("https://github.com/julianpeeters/avro-scala-runtime-typeprovider"))

pomExtra := (
      <scm>
        <url>git://github.com/julianpeeters/avro-scala-runtime-typeprovider.git</url>
        <connection>scm:git://github.com/julianpeeters/avro-scala-runtime-typeprovider.git</connection>
      </scm>
      <developers>
        <developer>
          <id>julianpeeters</id>
          <name>Julian Peeters</name>
          <url>http://github.com/julianpeeters</url>
        </developer>
      </developers>)
