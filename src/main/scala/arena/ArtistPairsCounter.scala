package arena

import scala.collection.mutable

object ArtistPairsCounter {

  def findPairs(linesIterator: Iterator[String], withCount: Int) = {

    val foundArtistsAndSize = pairsWithSize(linesIterator, withCount)
    // make the results deterministic ( not random order) by sorting
    foundArtistsAndSize.keys.toList.sortBy(a => (a.artist1, a.artist2))
  }

  // now can test with count !
  def pairsWithSize(linesIterator: Iterator[String], withCount: Int) = {
    val allPairsIterator: Iterator[ArtistPair] = linesIterator.flatMap {
      theLine =>
        val wordsInLine = theLine.split(",").toList
        val combinedForLine = createPairs(wordsInLine)
        combinedForLine
    }
    val countMap = iterativeMap(allPairsIterator)
    val foundArtists = countMap.filter{case (k, v) => v >= withCount}
    foundArtists
  }


  // iterative approach - lazily ( iterator ) evaluate each pair and build the map,
  // so at any time you only need to have enough memory to hold the map
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

  override def equals(that: Any): Boolean = { // make sure (A,B) == (B,A)

    that match {

      case a: ArtistPair =>  {
        ((this.artist1 == a.artist1) || (this.artist1 == a.artist2) ) &&
          ((this.artist2 == a.artist1) || (this.artist2 == a.artist2) )
      }

      case _ => false

    }
  }

  override def hashCode(): Int = {
    this.artist1.hashCode + this.artist2.hashCode + 31
  }
}

// case class ThreeArtist(artist1:String, artist2:String, artist3:String) -- if we needed 3 etc
