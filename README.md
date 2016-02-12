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
resolvers += Resolver.bintrayRepo("kipsigman", "sbt-plugins")

addSbtPlugin("kipsigman" % "sbt-elastic-beanstalk" % "0.1.1")
```

In your `build.sbt` enable the plugin.

```scala
enablePlugins(ElasticBeanstalk)
```

## Usage

1. Add build settings for the [SBT Native Packager Docker plugin](http://www.scala-sbt.org/sbt-native-packager/formats/docker.html).
2. Create distribution for upload to Elastic Beanstalk:

```sh
sbt elastic-beanstalk:dist
```

3. The `elastic-beanstalk:dist` command will output the location of your deployable zip. Upload to your Elastic Beanstalk environment.
4. Enjoy a beer or re-watch the original "Point Break."