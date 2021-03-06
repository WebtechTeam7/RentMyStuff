name := """RentMyStuff"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
javaJdbc,
jdbc,
javaEbean,
cache,
javaWs,
ws,
"postgresql" % "postgresql" % "9.1-901-1.jdbc4",
"org.xerial" % "sqlite-jdbc" % "3.7.15-M1",
"org.mindrot" % "jbcrypt" % "0.3m"
)
