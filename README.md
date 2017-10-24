# sbt-elastic-beanstalk-plugin
Creates a distribution of your Scala/Play/Akka app for deployment on AWS Amazon Web Services) Elastic Beanstalk.

## Features

* Build deployable zip for AWS (Amazon Web Services) Elastic Beanstalk environments with Docker config
  * Works with any Scala, Play Framework, or Akka application
  * Extends [SBT Native Packager Docker plugin](http://www.scala-sbt.org/sbt-native-packager/formats/docker.html)
  * Allows addition of Elastic Beanstalk config (Dockerrun.aws.json)

## Installation

Add the following to your `project/plugins.sbt` file:

```scala
resolvers += Resolver.url("bintray-kipsigman-sbt-plugins", url("http://dl.bintray.com/kipsigman/sbt-plugins"))(Resolver.ivyStylePatterns)

addSbtPlugin("kipsigman" % "sbt-elastic-beanstalk" % "0.1.4")
```

In your `build.sbt` enable the plugin.

```scala
enablePlugins(ElasticBeanstalkPlugin)
```

## Usage

1. Add `build.sbt` settings for the [SBT Native Packager Docker plugin](http://www.scala-sbt.org/sbt-native-packager/formats/docker.html).

  example (for a Play app):
  
  ```scala
  maintainer in Docker := "Johnny Utah <johnny.utah@fbi.gov>"
  dockerExposedPorts := Seq(9000)
  dockerBaseImage := "java:latest"
  ```

2. Create distribution for upload to Elastic Beanstalk:

```sh
sbt elastic-beanstalk:dist
```

3. The `elastic-beanstalk:dist` command will output the location of your deployable zip. Upload to your Elastic Beanstalk environment.
4. Enjoy a beer or re-watch the original "Point Break."

## Sample projects

Here are some sample projects using this plugin which may be helpful in getting started.

- [Play Framework sample](https://github.com/kipsigman/play-elastic-beanstalk)
