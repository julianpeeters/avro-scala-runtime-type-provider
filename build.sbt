name := "avro-scala-runtime-typeprovider"

version := "0.0.1"

organization := "com.julianpeeters"

scalaVersion := "2.10.4"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq( 
  "com.julianpeeters" %% "case-class-generator" % "0.6",
  "org.apache.avro" % "avro" % "1.7.7",
  "org.slf4j" % "slf4j-simple" % "1.7.5", 
  "org.specs2" %% "specs2" % "2.4" % "test",
  "com.novus" %% "salat" % "1.9.9" //% "test"
)

