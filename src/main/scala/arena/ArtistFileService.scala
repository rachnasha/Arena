package arena

import scala.io.Source
import java.io._

/**
  * Responsible for reading and writing from/to file.
  */

object ArtistFileService extends App {


  if (args.length < 2) {
    throw new RuntimeException(s"Need atleast 2 arguments, Input File and Output File")
  } else {

    val inputFile = args(0)
    val outputFile = args(1)
    val mayBeCount = if (args.length == 3) {
      //args(2).toIntOption // ahh need 2.13
      try {
        Option(args(2).toInt)
      } catch {
        case e: NumberFormatException => None
      }

    } else {
      None
    }

    mayBeCount.fold(ArtistFileService(inputFile, outputFile).run) {
      c => ArtistFileService(inputFile, outputFile, c).run
    }

  }


  case class ArtistFileService(inputFilePath: String, outputFilePath: String, countGreaterThanEqualTo: Int = 50) {

    def run(): Unit = {

      val bufferedReader = Source.fromFile(inputFilePath)
      val linesInFileIterator: Iterator[String] = bufferedReader.getLines()
      val foundArtist: List[ArtistPair] = ArtistPairsCounter.findPairs(linesInFileIterator, countGreaterThanEqualTo)
      bufferedReader.close()
      if (foundArtist.isEmpty) {
        println(s"Could not find any pairs in the file :${inputFilePath} having min count ${countGreaterThanEqualTo}")
        println("Nothing to write in output file")

      } else {
        println(s"Found ${foundArtist.size} pairs in :${inputFilePath} having min count ${countGreaterThanEqualTo}")
        writeToFile(outputFilePath, foundArtist)
        println("Done writing to output file")
      }
    }


    private def writeToFile(filePath: String, artistPairs: List[ArtistPair]): Unit = {

      val file = new File(filePath)
      val bw = new BufferedWriter(new FileWriter(file))
      for (pair <- artistPairs) {
        bw.write(pair.toString)
        bw.newLine()
      }

      bw.close()
    }
  }


}
