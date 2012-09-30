object recfun_worksheet {
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r)
      return 1;
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)
  }                                               //> pascal: (c: Int, r: Int)Int
  
  pascal(1, 3)                                    //> res0: Int = 3
  
  
  
  def balance(chars: List[Char]): Boolean = chars.foldLeft(0) {
    case (0, ')') => return false
    case (x, ')') => x - 1
    case (x, '(') => x + 1
    case (x, _  ) => x
  } == 0                                          //> balance: (chars: List[Char])Boolean
  
  balance("())(".toList)                          //> res1: Boolean = false
  
  
  5 % 6                                           //> res2: Int(5) = 5
}