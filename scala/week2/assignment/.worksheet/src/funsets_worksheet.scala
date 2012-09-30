
object funsets_worksheet {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(46); 
  println("hello")
  type Set = Int => Boolean;$skip(82); 

  def contains(s: Set, elem: Int): Boolean = s(elem);System.out.println("""contains: (s: Int => Boolean, elem: Int)Boolean""");$skip(61); 
  def singletonSet(elem: Int): Set = (el: Int) => el == elem;System.out.println("""singletonSet: (elem: Int)Int => Boolean""");$skip(87); 
  def union(s: Set, t: Set): Set =
    (el: Int) => contains(s, el) || contains(t, el);System.out.println("""union: (s: Int => Boolean, t: Int => Boolean)Int => Boolean""");$skip(91); 
  def intersect(s: Set, t: Set): Set =
    (el: Int) => contains(s, el) && contains(t, el);System.out.println("""intersect: (s: Int => Boolean, t: Int => Boolean)Int => Boolean""");$skip(87); 
  def diff(s: Set, t: Set): Set =
    (el: Int) => contains(s, el) && !contains(t, el);System.out.println("""diff: (s: Int => Boolean, t: Int => Boolean)Int => Boolean""");$skip(89); 
  def filter(s: Set, p: Int => Boolean): Set =
    (el: Int) => contains(s, el) && s(el);System.out.println("""filter: (s: Int => Boolean, p: Int => Boolean)Int => Boolean""");$skip(210); 
  
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > 1000) true
      else if (contains(s, a) && !p(a)) false
      else iter(a + 1)
    }
    iter(-1000)
  };System.out.println("""forall: (s: Int => Boolean, p: Int => Boolean)Boolean""");$skip(72); 
  
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, !p(_));System.out.println("""exists: (s: Int => Boolean, p: Int => Boolean)Boolean""");$skip(66); 
  def map(s: Set, f: Int => Int): Set = x => exists(s, f(_) == x);System.out.println("""map: (s: Int => Boolean, f: Int => Int)Int => Boolean""");$skip(134); 
  def toString(s: Set): String = {
    val xs = for (i <- -1000 to 1000 if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  };System.out.println("""toString: (s: Int => Boolean)String""");$skip(35); 
  
  
  
  val s = singletonSet(1);System.out.println("""s  : Int => Boolean = """ + $show(s ));$skip(20); val res$0 = 
  
  contains(s, 1);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(29); 
  
  val t = singletonSet(2);System.out.println("""t  : Int => Boolean = """ + $show(t ));$skip(22); 
  val u = union(s, t);System.out.println("""u  : Int => Boolean = """ + $show(u ));$skip(26); 
  val i = intersect(s, t);System.out.println("""i  : Int => Boolean = """ + $show(i ));$skip(17); val res$1 = 
  contains(u, 1);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(17); val res$2 = 
  contains(u, 2);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(17); val res$3 = 
  contains(u, 3);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(17); val res$4 = 
  contains(u, 4);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(36); 
  
  
  
  val s1 = singletonSet(1);System.out.println("""s1  : Int => Boolean = """ + $show(s1 ));$skip(27); 
  val s2 = singletonSet(2);System.out.println("""s2  : Int => Boolean = """ + $show(s2 ));$skip(27); 
  val s3 = singletonSet(3);System.out.println("""s3  : Int => Boolean = """ + $show(s3 ));$skip(55); val res$5 = 
  
  toString(map(union(union(s1, s2), s3), x => 2*x));System.out.println("""res5: String = """ + $show(res$5));$skip(39); 
  
  val ss = union(union(s1, s2), s3);System.out.println("""ss  : Int => Boolean = """ + $show(ss ));$skip(18); val res$6 = 
  contains(ss, 1);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(18); val res$7 = 
  contains(ss, 2);System.out.println("""res7: Boolean = """ + $show(res$7))}
}