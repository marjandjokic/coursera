/**
 *
 */
/**
 * @author Gerasimos Athanasopoulos
 *
 */

package coursera.unionfind


class QuickFindUF(val N: Int) {
	var ids = 0 to N - 1 toArray

	def connected(p: Int, q: Int) = ids(p) == ids(q)

	def	union(p: Int, q: Int) =
	  for{
	    i <- 0 until ids.length
	    qid = ids(q)
	    pid = ids(p)
	    if ids(i) == pid
	  } ids(i) = qid
}