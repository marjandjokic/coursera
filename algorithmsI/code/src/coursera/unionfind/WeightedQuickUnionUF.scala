/**
 *
 */
/**
 * @author Gerasimos Athanasopoulos
 *
 */

package coursera.unionfind


class WeightedQuickUnionUF(val N: Int) {
	var ids = 0 to N - 1 toArray
	var szs = Array.fill(N)(1)
	
	def root(num: Int): Int =
	  if (num == ids(num)) num else root(ids(num))

	def connected(p: Int, q: Int) = root(p) == root(q)
	
	def	union(p: Int, q: Int) = {
	  val proot = root(p)
	  val qroot = root(q)
	  if (szs(proot) < szs(qroot)) {
	    ids(proot) = qroot
	    szs(qroot) += szs(proot)
	  } else {
		ids(qroot) = proot
	    szs(proot) += szs(qroot)
	  }
	}
}
