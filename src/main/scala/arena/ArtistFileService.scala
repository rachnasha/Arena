package arena

import scala.io.Source
import java.io._

/**
  * Responsible for reading and writing from/to file.
  */

object ArtistFileService {


    def apply(inputFilePath: String, outputFilePath:String, countGreaterThanEqualTo: Int = 50): Unit = {

      val bufferedReader = Source.fromFile(inputFilePath)
      val linesInFileIterator: Iterator[String] = bufferedReader.getLines()
      val foundArtist: List[ArtistPair] = ArtistPairsCounter.findPairs(linesInFileIterator, countGreaterThanEqualTo)
      bufferedReader.close()
      if(foundArtist.isEmpty){
        println(s"Could not find any pairs in the file :${inputFilePath} having min count ${countGreaterThanEqualTo}")
        println("Nothing to write in output file")

      }else {
        println(s"Found ${foundArtist.size} pairs in :${inputFilePath} having min count ${countGreaterThanEqualTo}")
        writeToFile(outputFilePath, foundArtist)
        println("Done writing to output file")
      }
    }


   def writeToFile(filePath:String, artistPairs: List[ArtistPair]): Unit = {

     val file = new File(filePath)
     val bw = new BufferedWriter(new FileWriter(file))
     for(pair <- artistPairs){
       bw.write(pair.toString)
       bw.newLine()
     }

     bw.close()
   }




}
