/**
 *
 */
/**
 * @author Gerasimos Athanasopoulos
 *
 */

package coursera.unionfind


class QuickUnionUF(val N: Int) {
	var ids = 0 to N - 1 toArray

	def root(num: Int): Int =
	  if (num == ids(num)) num else root(ids(num))

	def connected(p: Int, q: Int) = root(p) == root(q)
	
	def	union(p: Int, q: Int) = ids(root(p)) = root(q)
	
}