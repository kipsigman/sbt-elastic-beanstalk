package kipsigman.sbt.elasticbeanstalk

import sbt._

trait ElasticBeanstalkKeys {
  val elasticBeanstalkPackageMappings = TaskKey[Seq[(File, String)]]("elastic-beanstalk-package-mappings", "Generates location mappings for Elastic Beanstalk build.")
}

object ElasticBeanstalkKeys extends ElasticBeanstalkKeys