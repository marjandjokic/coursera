object recfun_worksheet {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(164); 
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r)
      return 1;
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)
  };System.out.println("""pascal: (c: Int, r: Int)Int""");$skip(18); val res$0 = 
  
  pascal(1, 3);System.out.println("""res0: Int = """ + $show(res$0));$skip(193); 
  
  
  
  def balance(chars: List[Char]): Boolean = chars.foldLeft(0) {
    case (0, ')') => return false
    case (x, ')') => x - 1
    case (x, '(') => x + 1
    case (x, _  ) => x
  } == 0;System.out.println("""balance: (chars: List[Char])Boolean""");$skip(28); val res$1 = 
  
  balance("())(".toList);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(14); val res$2 = 
  
  
  5 % 6;System.out.println("""res2: Int(5) = """ + $show(res$2))}
}