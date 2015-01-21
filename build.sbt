name := """RentMyStuff"""

version := "1.0-SNAPSHOT"
val postgresql = "postgresql" % "postgresql" % "9.3-1102.jdbc
lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
javaJdbc,
jdbc,
javaEbean,
cache,
javaWs,
ws,
postgresql,
"org.mindrot" % "jbcrypt" % "0.3m"
)
