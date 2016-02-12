package kipsigman.sbt.elasticbeanstalk

import sbt._
import sbt.Keys._

import com.typesafe.sbt.SbtNativePackager.Docker
import com.typesafe.sbt.SbtNativePackager.Universal
import com.typesafe.sbt.packager.Keys._
import com.typesafe.sbt.packager.MappingsHelper
import com.typesafe.sbt.packager.Stager
import com.typesafe.sbt.packager.docker.DockerPlugin
import com.typesafe.sbt.packager.universal.Archives

/**
 * == Elastic Beanstalk Plugin ==
 *
 * This plugin helps you build a dist for an Amazon Elastic Beanstalk environment with Docker configuration.
 *
 * == Configuration ==
 *
 * In order to configure this plugin take a look at the available [[kipsigman.sbt.elasticbeanstalk.ElasticBeanstalkKeys]]
 *
 * == Requirements ==
 *
 * This plugin depends on the SBT Native Packager DockerPlugin.
 * @see http://www.scala-sbt.org/sbt-native-packager/formats/docker.html
 *
 * == Usage ==
 * @example Enable the plugin in the `build.sbt`
 * {{{
 *    enablePlugins(ElasticBeanstalkPlugin)
 * }}}
 */
object ElasticBeanstalkPlugin extends AutoPlugin {
  
  override def requires = DockerPlugin

  object autoImport extends ElasticBeanstalkKeys {
    val ElasticBeanstalk = config("elastic-beanstalk") extend Docker
  }
  
  import autoImport._
  
  override lazy val projectSettings: Seq[Def.Setting[_]] = inConfig(ElasticBeanstalk)(
    Seq(
      dist <<= (packageBin, streams) map printDist,
      elasticBeanstalkPackageMappings <<= sourceDirectory map { dir =>
        MappingsHelper contentOf dir
      },
      mappings := ((mappings in Docker).value ++ elasticBeanstalkPackageMappings.value),
      mappings in packageBin <<= stage map { dir =>
        MappingsHelper contentOf dir
      },
      packageBin <<= (target, packageName, mappings in packageBin) map makeZip,
      packageName := (packageName in Universal).value,
      sourceDirectory := baseDirectory.value / ElasticBeanstalk.name,
      stage <<= (streams, stagingDirectory, mappings) map Stager.stage(ElasticBeanstalk.name),
      stagingDirectory := (stagingDirectory in Docker).value,
      target := target.value / ElasticBeanstalk.name
    )
  )
  
  private[this] def makeZip(target: File, name: String, mappings: Seq[(File, String)]): File = {
    Archives.makeNativeZip(target, name, mappings, None, Seq.empty)
  }
  
  private[this] def printDist(dist: File, streams: TaskStreams): File = {
    streams.log.info("")
    streams.log.info("Your package is ready in " + dist.getCanonicalPath)
    streams.log.info("")
    dist
  }
  
//  private[this] def packageBin(target: File, packageName: String, stage: File): File = {
//    // Create target
//    IO.createDirectory(target)
//    
//    // Zip staging contents
//    val zipFile: File = target / s"${packageName}.zip"
//    
//    Process(s"zip -r $zipFile .", elasticBeanstalkStagingDirectory) !!
//    
//    zipFile
//  }
  
}