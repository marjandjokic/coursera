object Anagrams {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(57); 
  val l = List("some", "words", "here");System.out.println("""l  : List[java.lang.String] = """ + $show(l ));$skip(26); 
  val s = "ffooobbbfffar";System.out.println("""s  : java.lang.String = """ + $show(s ));$skip(56); val res$0 = 
  s.toList.groupBy(identity).mapValues(_.length).toList;System.out.println("""res0: List[(Char, Int)] = """ + $show(res$0));$skip(56); val res$1 = 
  s.groupBy(identity).mapValues(_.length).toList.sorted;System.out.println("""res1: List[(Char, Int)] = """ + $show(res$1))}
  
}