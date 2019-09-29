import java.io._
import scala.io._
import java.nio.file.Files
import scala.collection.mutable.ArrayBuffer
import sys.process._
import java.nio.charset.StandardCharsets
import scala.collection.JavaConverters._

object Find404 {
  def main(args: Array[String]): Unit = {
    val a = getFileNames
      .map(new File(_))
      .map(readFile)
      .foreach (println)
      
  }

  def getFileNames: Array[String] = ("git ls-files --full-name".!!).split("\n")

  def readFile(fileName: File): String = {
    val source = Source.fromFile(fileName)
    val lines = try source.mkString finally source.close() //if file is too large, could run out of memory
    lines
  }
}
