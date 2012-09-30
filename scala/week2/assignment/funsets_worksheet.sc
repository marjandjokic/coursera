
object funsets_worksheet {
  println("hello")                                //> hello
  type Set = Int => Boolean

  def contains(s: Set, elem: Int): Boolean = s(elem)
                                                  //> contains: (s: Int => Boolean, elem: Int)Boolean
  def singletonSet(elem: Int): Set = (el: Int) => el == elem
                                                  //> singletonSet: (elem: Int)Int => Boolean
  def union(s: Set, t: Set): Set =
    (el: Int) => contains(s, el) || contains(t, el)
                                                  //> union: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  def intersect(s: Set, t: Set): Set =
    (el: Int) => contains(s, el) && contains(t, el)
                                                  //> intersect: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  def diff(s: Set, t: Set): Set =
    (el: Int) => contains(s, el) && !contains(t, el)
                                                  //> diff: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  def filter(s: Set, p: Int => Boolean): Set =
    (el: Int) => contains(s, el) && s(el)         //> filter: (s: Int => Boolean, p: Int => Boolean)Int => Boolean
  
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > 1000) true
      else if (contains(s, a) && !p(a)) false
      else iter(a + 1)
    }
    iter(-1000)
  }                                               //> forall: (s: Int => Boolean, p: Int => Boolean)Boolean
  
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, !p(_))
                                                  //> exists: (s: Int => Boolean, p: Int => Boolean)Boolean
  def map(s: Set, f: Int => Int): Set = x => exists(s, f(_) == x)
                                                  //> map: (s: Int => Boolean, f: Int => Int)Int => Boolean
  def toString(s: Set): String = {
    val xs = for (i <- -1000 to 1000 if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }                                               //> toString: (s: Int => Boolean)String
  
  
  
  val s = singletonSet(1)                         //> s  : Int => Boolean = <function1>
  
  contains(s, 1)                                  //> res0: Boolean = true
  
  val t = singletonSet(2)                         //> t  : Int => Boolean = <function1>
  val u = union(s, t)                             //> u  : Int => Boolean = <function1>
  val i = intersect(s, t)                         //> i  : Int => Boolean = <function1>
  contains(u, 1)                                  //> res1: Boolean = true
  contains(u, 2)                                  //> res2: Boolean = true
  contains(u, 3)                                  //> res3: Boolean = false
  contains(u, 4)                                  //> res4: Boolean = false
  
  
  
  val s1 = singletonSet(1)                        //> s1  : Int => Boolean = <function1>
  val s2 = singletonSet(2)                        //> s2  : Int => Boolean = <function1>
  val s3 = singletonSet(3)                        //> s3  : Int => Boolean = <function1>
  
  toString(map(union(union(s1, s2), s3), x => 2*x))
                                                  //> res5: String = {2,4,6}
                                                  //| Output exceeds cutoff limit. 
  
  val ss = union(union(s1, s2), s3)
  contains(ss, 1)
  contains(ss, 2)
}