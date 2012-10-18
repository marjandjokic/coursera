import patmat._

object patmat_worksheet {

  val sampleTree = Huffman.makeCodeTree(
	  Huffman.makeCodeTree(Huffman.Leaf('x', 1), Huffman.Leaf('e', 1)),
	  Huffman.Leaf('t', 2)
	)                                         //> sampleTree  : patmat.Huffman.Fork = Fork(Fork(Leaf(x,1),Leaf(e,1),List(x, e)
                                                  //| ,2),Leaf(t,2),List(x, e, t),4)
  sampleTree                                      //> res0: patmat.Huffman.Fork = Fork(Fork(Leaf(x,1),Leaf(e,1),List(x, e),2),Leaf
                                                  //| (t,2),List(x, e, t),4)
 
  val l = List('a', 'b', 'a', 'd', 'h', 'a')      //> l  : List[Char] = List(a, b, a, d, h, a)
  
  Huffman.combine(Huffman.makeOrderedLeafList(Huffman.times(l)))
                                                  //> res1: List[patmat.Huffman.CodeTree] = List(Leaf(h,1), Fork(Leaf(b,1),Leaf(d,
                                                  //| 1),List(b, d),2), Leaf(a,3))
   Huffman.decodedSecret                          //> res2: List[Char] = List(h, u, f, f, m, a, n, e, s, t, c, o, o, l)
}