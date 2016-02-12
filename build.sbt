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

publishMavenStyle := true
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}


ScriptedPlugin.scriptedSettings
scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Dplugin.version=" + version.value)
}
scriptedBufferLog := false
