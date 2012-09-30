package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1) 
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = chars.foldLeft(0) {
    case (0, ')') => return false
    case (x, ')') => x - 1
    case (x, '(') => x + 1
    case (x, _  ) => x
  } == 0

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (coins.isEmpty || money < 0) 0
    else if (money == 0) 1
    else
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
}
