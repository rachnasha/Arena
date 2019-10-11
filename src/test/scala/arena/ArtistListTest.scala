import arena.{ArtistPair, ArtistPairsCounter}
import org.scalatest._


class ArtistListTest extends FlatSpec with Matchers {

  "ArtistList" should "be able to read a get data from a simpleList" in {

    val testLine = "Radiohead,Pulp,Morrissey,Delays,Stereophonics,Blur,Suede,Sleeper,The La's,Super Furry Animals,Iggy Pop\nBand of Horses,Smashing Pumpkins,The Velvet Underground,Radiohead,The Decemberists,Morrissey,Television\n"

    val inputLinesIter = testLine.split("\n").iterator

    val foundArtists = ArtistPairsCounter.findPairs(inputLinesIter, 2)

    foundArtists.size should be (1)
    foundArtists.head should be (ArtistPair("Radiohead", "Morrissey"))
  }


  it should "be able to handle a medium input" in {


    val mediumInput = "Michael Bublé,Jason Mraz,光田康典,The National,Sarah McLachlan,Claude Debussy,Lady Gaga,周杰倫,植松伸夫,Indochine,Rise Against,City and Colour,Radiohead,Red Hot Chili Peppers,Alexisonfire,Bebo & Cigala,The Mars Volta,Chick Corea & Hiromi,Theory of a Deadman,Cantaloupe Island,Adam's Apple,A Brighter Day,Afrodisia,Mambo De La Pinta,Greg Osby,James Newton,Testify,Art Blakey,Caravan,Tsuyoshi Sekito,Mira,Temptation,A Night In Tunisia,Serj Tankian,Winter,Duke Pearson,Cantaloop (Flip Fantasia),Cæcilie Norby,El Cumbanchero,You Don't Know What Love Is,Death Letter,Art Taylor,Closer to Home,Indio Gitano,Solomon Ilori and his Afro-Drum Ensemble,Black Byrd,Far West,Hi-Heel Sneakers,Congalegra,There Is The Bomb\nThe Beatles,Chase Coy,Stephen Jerzak,nevershoutnever!,Taylor Swift,The Maine,All Time Low,Boys Like Girls,McFly,Owl City,Glee Cast,Brokencyde,Ke$ha,Relient K,Cobra Starship,Jason Mraz,Across The Universe Soundtrack,Romance On A Rocketship,Hellogoodbye,Avril Lavigne,Show Me The Skyline,Hey Monday,Sterling Knight,Kate Nash,Plain White T's,Breathe Carolina,Parachute,HEVO84,Stereo Skyline,Mika,The Weird Sisters,The Kooks,The Scene Aesthetic,Lady Gaga,Fake Number,High School Musical 3,3OH!3,Devon Werkheiser,Bring Me The Horizon,Dear Juliet,Replace,Phoebe Buffay,Son of Dork,Vampire Weekend,blink-182,A Rocket To The Moon,Oasis,John Williams,Miley Cyrus,Lady Antebellum\nAnimal Collective,Joy Division,Ariel Pink's Haunted Graffiti,Fang Island,The Courteeners,Xiu Xiu,The Velvet Underground,Sleigh Bells,Belle and Sebastian,Portishead,Neutral Milk Hotel,Wire,Deerhunter,Ramones,65daysofstatic,The Pains of Being Pure at Heart,The Cure,The Radio Dept.,Avi Buffalo,Wavves,Real Estate,The Jesus and Mary Chain,The Tallest Man on Earth,Clark,The Morning Benders,The Human League,Fuck Buttons,The Libertines,Burial,Broken Bells,The Go-Betweens,The Smiths,Beach House,Beirut,Crystal Castles,God Is an Astronaut,Beth Gibbons & Rustin Man,Balmorhea,Pulp,Fleet Foxes,Frightened Rabbit,Vampire Weekend,Chrome,Midlake,Flying Lotus,Panda Bear,Caribou,Micachu,Motörhead,Girls\nBob Seger,30 Seconds to Mars,Crossfade,Staind,Dashboard Confessional,Eddie Money,Local H,Fuel,The Starting Line,Theory of a Deadman,Trapt,Boston,.38 Special,Sick Puppies,Smile Empty Soul,Chingy,Don Ross,Baby Bash,Hinder,Wolfgang Amadeus Mozart,Rod Stewart,Nickelback,Incubus,Jimmy Eat World,REO Speedwagon,TRUSTcompany,Tommy Emmanuel,Jackson Browne,Collective Soul,The Calling,Seven Mary Three,Pure Prairie League,Candlebox,Breaking Benjamin,Mario,Shinedown,Doyle Dykes,The J. Geils Band,Submersed,Hawthorne Heights,10 Years,Andy McKee,Craig D'Andrea,Nelly,Scarface,Johann Sebastian Bach,Megadeth,Slayer,Don Henley,Eagles\n"
    val inputLinesIter =  mediumInput.split("\n").iterator

    val foundArtistPairs = ArtistPairsCounter.findPairs(inputLinesIter, 2)

    foundArtistPairs.size should be (1)
    foundArtistPairs.head should be (ArtistPair("Jason Mraz","Lady Gaga"))



  }

}
