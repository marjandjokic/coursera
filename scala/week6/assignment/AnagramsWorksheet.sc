import forcomp._


object AnagramsWorksheet {
  val l = List("some", "words", "here")           //> l  : List[java.lang.String] = List(some, words, here)
  val s = "ffooobbbfffar"                         //> s  : java.lang.String = ffooobbbfffar
  s.toList.groupBy(identity).mapValues(_.length).toList
                                                  //> res0: List[(Char, Int)] = List((f,5), (a,1), (b,3), (r,1), (o,3))
  s.groupBy(identity).mapValues(_.length).toList.sorted
                                                  //> res1: List[(Char, Int)] = List((a,1), (b,3), (f,5), (o,3), (r,1))
  Anagrams.sentenceOccurrences(l)                 //> res2: forcomp.Anagrams.Occurrences = List((d,1), (e,3), (h,1), (m,1), (o,2),
                                                  //|  (r,2), (s,2), (w,1))
  List(List('l', 'r'), List('l', 'r')).groupBy(identity)
                                                  //> res3: scala.collection.immutable.Map[List[Char],List[List[Char]]] = Map(List
                                                  //| (l, r) -> List(List(l, r), List(l, r)))

 val ll = List(('a', 2), ('b', 2))                //> ll  : List[(Char, Int)] = List((a,2), (b,2))
 Anagrams.combinations(ll)                        //> res4: List[forcomp.Anagrams.Occurrences] = List(List(), List((a,1)), List((a
                                                  //| ,2)), List((b,1)), List((b,2)), List((a,1), (b,1)), List((a,2), (b,1)), List
                                                  //| ((a,1), (b,2)), List((a,2), (b,2)))
 ll.combinations(2).toList                        //> res5: List[List[(Char, Int)]] = List(List((a,2), (b,2)))
 
 
 val x = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
                                                  //> x  : List[(Char, Int)] = List((a,1), (d,1), (l,1), (r,1))
 val y = List(('d', 1))                           //> y  : List[(Char, Int)] = List((d,1))
 
 Anagrams.subtract(x, y)                          //> res6: forcomp.Anagrams.Occurrences = List((a,1), (l,1), (r,1))
}