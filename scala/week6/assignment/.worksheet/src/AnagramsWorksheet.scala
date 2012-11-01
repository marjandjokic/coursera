import forcomp._


object AnagramsWorksheet {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(85); 
  val l = List("some", "words", "here");System.out.println("""l  : List[java.lang.String] = """ + $show(l ));$skip(26); 
  val s = "ffooobbbfffar";System.out.println("""s  : java.lang.String = """ + $show(s ));$skip(56); val res$0 = 
  s.toList.groupBy(identity).mapValues(_.length).toList;System.out.println("""res0: List[(Char, Int)] = """ + $show(res$0));$skip(56); val res$1 = 
  s.groupBy(identity).mapValues(_.length).toList.sorted;System.out.println("""res1: List[(Char, Int)] = """ + $show(res$1));$skip(34); val res$2 = 
  Anagrams.sentenceOccurrences(l);System.out.println("""res2: forcomp.Anagrams.Occurrences = """ + $show(res$2));$skip(57); val res$3 = 
  List(List('l', 'r'), List('l', 'r')).groupBy(identity);System.out.println("""res3: scala.collection.immutable.Map[List[Char],List[List[Char]]] = """ + $show(res$3));$skip(36); 

 val ll = List(('a', 2), ('b', 2));System.out.println("""ll  : List[(Char, Int)] = """ + $show(ll ));$skip(27); val res$4 = 
 Anagrams.combinations(ll);System.out.println("""res4: List[forcomp.Anagrams.Occurrences] = """ + $show(res$4));$skip(27); val res$5 = 
 ll.combinations(2).toList;System.out.println("""res5: List[List[(Char, Int)]] = """ + $show(res$5));$skip(58); 
 
 
 val x = List(('a', 1), ('d', 1), ('l', 1), ('r', 1));System.out.println("""x  : List[(Char, Int)] = """ + $show(x ));$skip(24); 
 val y = List(('d', 1));System.out.println("""y  : List[(Char, Int)] = """ + $show(y ));$skip(27); val res$6 = 
 
 Anagrams.subtract(x, y);System.out.println("""res6: forcomp.Anagrams.Occurrences = """ + $show(res$6))}
}