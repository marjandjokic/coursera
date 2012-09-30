/**
 *
 */
/**
 * @author Gerasimos Athanasopoulos
 *
 */


class TestTiming(max: Int, seed: Int) {
	
	def	csv(num: Int) = {
	  run(num).mkString(",")
	}
	
	def time(f: => Unit) = {
	  val s = System.currentTimeMillis
	  f
	  System.currentTimeMillis - s
	}
	
	def	run(n: Int): Array[Long] = {
	  val arr = Array(time { Timing.trial(n, seed) })

	  if (n*2 < max) { arr ++ run(n*2) } 
	  else 			 { arr }
	}
}