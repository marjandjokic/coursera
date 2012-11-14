import streams._

object bloxorz {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  printf("fasdfasdfdas")
  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  };$skip(1078); 
  
  val l = new Level1 {
    val neighbors = neighborsWithHistory(Block(Pos(1, 1), Pos(1, 1)), List(Left, Up)).toSet
    val result = Set((Block(Pos(1,2),Pos(1,3)), List(Right,Left, Up)), (Block(Pos(2,1),Pos(3,1)), List(Down, Left, Up)))
  };System.out.println("""l  : java.lang.Object with bloxorz.Level1{val neighbors: scala.collection.immutable.Set[(this.Block, List[this.Move])]; val result: scala.collection.immutable.Set[(this.Block, List[Product with Serializable with this.Move])]} = """ + $show(l ));$skip(15); val res$0 = 
   l.neighbors;System.out.println("""res0: scala.collection.immutable.Set[(bloxorz.l.Block, List[bloxorz.l.Move])] = """ + $show(res$0))}
   
}