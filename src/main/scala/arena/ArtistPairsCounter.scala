package arena

import scala.collection.mutable

object ArtistPairsCounter {

  def findPairs(linesIterator: Iterator[String], withCount: Int) = {

    val allPairsIterator: Iterator[ArtistPair] = linesIterator.flatMap {
      theLine =>
        val wordsInLine = theLine.split(",").toList
        val combinedForLine = createPairs(wordsInLine)
        combinedForLine
    }

    // functional approach - materialize the list and hold it all in memory and then group them
    // val countMap = functionalMap(artistPairs)

    // iterative approach - lazily ( iterator ) evaluate each pair and build the map,
    // so at any time you only need to have enough memory to hold the map
    val countMap = iterativeMap(allPairsIterator)
    val foundArtists = countMap.filter {case (k, v) => v >= withCount}

    // make the results deterministic ( not random order) by sorting
    foundArtists.keys.toList.sortBy(a => (a.artist1, a.artist2))
  }


  private def iterativeMap(artistPairs: Iterator[ArtistPair]): mutable.Map[ArtistPair, Int] = {
    val countMap = mutable.Map[ArtistPair, Int]()

    for (pairs <- artistPairs) {
      val counter = countMap.get(pairs).fold(1) {
        c => c + 1
      }
      countMap(pairs) = counter
    }

    countMap
  }


  private def functionalMap(artistPairs: Iterator[ArtistPair]): Map[ArtistPair, Int] = {
    artistPairs.toList.groupBy(identity).mapValues(_.size)
  }


  private def createPairs(ll: List[String]): List[ArtistPair] = {

    def pair(head: String, rest: List[String]): List[ArtistPair] = {
      rest.map { v => ArtistPair(head, v) }
    }

    ll match {
      case Nil => Nil
      case head :: Nil => Nil
      case head :: tail => pair(head, tail) ::: createPairs(tail)
    }
  }

}


case class ArtistPair(artist1: String, artist2: String) {

  override def toString: String = {s"${artist1},${artist2}"}
}

// case class ThreeArtist(artist1:String, artist2:String, artist3:String) -- if we needed 3 etc
