package pl.piomin.services.gatling.utils

import java.io.{File, PrintWriter}
import java.nio.file.{FileSystems, Files, Path, StandardOpenOption}

import io.gatling.core.Predef.{exec, _}

object SaveToFile {
  def saveAttributeFromSession(attributeName: String, fileName: String, csvHeader: String) = exec { session =>
    val attributeValue: String = session(attributeName).as[String]
    val newLineWithAttributeValue: String = attributeValue + "\n"
    createFileWithHeader(fileName, csvHeader)
    Files.write(getFileToStorePath(fileName), newLineWithAttributeValue.getBytes(), StandardOpenOption.APPEND)
    session
  }

  private def createFileWithHeader(fileName: String, csvHeader: String) = {
    if (Files.exists(getFileToStorePath(fileName))) {
      println("File exists: " + fileName)
      if (!Files.readAllLines(getFileToStorePath(fileName)).get(0).contains(csvHeader.trim)) {
        Files.write(getFileToStorePath(fileName), csvHeader.getBytes(), StandardOpenOption.APPEND)
      }
    } else {
      println("Creating new file: " + fileName)
      val pw = new PrintWriter(new File(getFileToStorePath(fileName).toString))
      pw.write(csvHeader)
      pw.close
    }
  }

  def getFileToStorePath(fileName: String): Path = {
    FileSystems.getDefault().getPath("src", "test", "resources", fileName)
  }
}
