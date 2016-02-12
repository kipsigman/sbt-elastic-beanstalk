import bintray.Keys._

sbtPlugin := true

name := "sbt-elastic-beanstalk"
organization := "kipsigman"

scalacOptions := Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.6")

licenses += ("Apache-2.0", url("https://github.com/kipsigman/sbt-elastic-beanstalk/blob/master/LICENSE"))
homepage := Some(url("https://github.com/kipsigman/sbt-elastic-beanstalk"))
scmInfo := Some(ScmInfo(url("https://github.com/kipsigman/sbt-elastic-beanstalk"), "scm:git:git://github.com:kipsigman/sbt-elastic-beanstalk.git"))

// Bintray Publish settings
seq(bintraySettings:_*)
repository in bintray := "kipsigman"

// Scripted for testing
ScriptedPlugin.scriptedSettings
scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Dplugin.version=" + version.value)
}
scriptedBufferLog := false