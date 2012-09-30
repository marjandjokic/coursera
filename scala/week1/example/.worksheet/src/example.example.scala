package example

object example {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(99); 
  def max(xs: List[Int]): Int = xs.reduceLeft((a, b) => a.max(b));System.out.println("""max: (xs: List[Int])Int""");$skip(17); val res$0 = 
  
  max(List());System.out.println("""res0: Int = """ + $show(res$0))}
  
}