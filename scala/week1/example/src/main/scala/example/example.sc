package example

object example {
  def max(xs: List[Int]): Int = xs.reduceLeft((a, b) => a.max(b))
                                                  //> max: (xs: List[Int])Int
  
  max(List())                                     //> java.lang.UnsupportedOperationException: empty.reduceLeft
                                                  //| 	at scala.collection.LinearSeqOptimized$class.reduceLeft(LinearSeqOptimiz
                                                  //| ed.scala:124)
                                                  //| 	at scala.collection.immutable.List.reduceLeft(List.scala:76)
                                                  //| 	at example.example$$anonfun$main$1.max$1(example.example.scala:4)
                                                  //| 	at example.example$$anonfun$main$1.apply$mcV$sp(example.example.scala:6)
                                                  //| 
                                                  //| 	at scala.runtime.WorksheetSupport$$anonfun$$execute$1.apply$mcV$sp(Works
                                                  //| heetSupport.scala:75)
                                                  //| 	at scala.runtime.WorksheetSupport$.redirected(WorksheetSupport.scala:64)
                                                  //| 
                                                  //| 	at scala.runtime.WorksheetSupport$.$execute(WorksheetSupport.scala:74)
                                                  //| 	at example.example$.main(example.example.scala:3)
                                                  //| 	at example.example.main(example.example.scala)
  
}