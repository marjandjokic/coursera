import patmat._

object patmat_worksheet {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(180); 

  val sampleTree = Huffman.makeCodeTree(
	  Huffman.makeCodeTree(Huffman.Leaf('x', 1), Huffman.Leaf('e', 1)),
	  Huffman.Leaf('t', 2)
	);System.out.println("""sampleTree  : patmat.Huffman.Fork = """ + $show(sampleTree ));$skip(13); val res$0 = 
  sampleTree;System.out.println("""res0: patmat.Huffman.Fork = """ + $show(res$0));$skip(47); 
 
  val l = List('a', 'b', 'a', 'd', 'h', 'a');System.out.println("""l  : List[Char] = """ + $show(l ));$skip(68); val res$1 = 
  
  Huffman.combine(Huffman.makeOrderedLeafList(Huffman.times(l)));System.out.println("""res1: List[patmat.Huffman.CodeTree] = """ + $show(res$1));$skip(25); val res$2 = 
   Huffman.decodedSecret;System.out.println("""res2: List[Char] = """ + $show(res$2))}
}