package find404

import java.io._
import java.net.{HttpURLConnection, URI}

import scala.io._
import scala.sys.process._
import scala.util.control.NonFatal

object App {
  def main(args: Array[String]): Unit = {
    getFileNames
      .map(new File(_))
      .map(readFile)
      .flatMap(getURLS)
      .map(x => (x, getStatusCode(x))) // mention
      .foreach(println(_))
  }

  def getFileNames: Array[String] = "git ls-files --full-name".!!.split("\n")

  def readFile(fileName: File): String = {
    println(fileName.getAbsolutePath)
    val source = Source.fromFile(fileName)
    val lines = {
      try {
        source.mkString
      } catch {
        case NonFatal(_) => ""
      } finally {
        source.close()
      }
    }

    //if file is too large, could run out of memory
    lines
  }

  def getURLS(text: String): List[URI] = {
    "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".r
      .findAllIn(text)
      .map(new URI(_))
      .toList
  }

  def getStatusCode(url: URI): Integer = {
    val http = url.toURL.openConnection()
    http.asInstanceOf[HttpURLConnection].getResponseCode()
  }
}
