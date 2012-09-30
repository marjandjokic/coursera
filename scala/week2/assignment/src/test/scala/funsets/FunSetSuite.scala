package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.8/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton contains")
      expect(contains(s1, -2), "Singleton not contains")(false)
    }
  }

  test("union") {
    new TestSets {
      val s12 = union(s1, s2)
      val s123 = union(s12, s3)
      assert(contains(s12, 1), "Union1 1")
      assert(contains(s12, 2), "Union1 2")
      assert(!contains(s12, 3), "Union1 3")
      assert(contains(s123, 1), "Union2 1")
      assert(contains(s123, 2), "Union2 2")
      assert(contains(s123, 3), "Union2 3")
    }
  }
  
  test("intersection") {
    new TestSets {
      val s = union(s1, s2)
      val t = union(s2, s3)
      val ss = intersect(s, t)
      assert(!contains(ss, 1), "Intersect 1")
      assert(contains(ss, 2), "Intersect 2")
      assert(!contains(ss, 3), "Intersect 3")
    }
  }
  
  test("difference") {
    new TestSets {
      val s = union(s1, s2)
      val t = union(s2, s3)
      val ss = diff(s, t)
      assert(contains(ss, 1), "Diff 1")
      assert(!contains(ss, 2), "Diff 2")
      assert(!contains(ss, 3), "Diff 3")
    }
  }

  test("filter") {
    new TestSets {
      val s = union(union(s1, s2), s3)
      val ss = filter(s, (y: Int) => y > 2)
      assert(!contains(ss, 1), "Filter 1")
      assert(!contains(ss, 2), "Filter 2")
      assert(contains(ss, 3), "Filter 3")
    }
  }

  test("forall") {
    new TestSets {
      val s = union(union(s1, s2), s3)
      assert(!forall(s, (y: Int) => y > 2), "Forall 1")
      assert(forall(s, (y: Int) => y > 0), "Forall 2")
      assert(!forall(s, (y: Int) => y < 3), "Forall 3")
    }
  }

  test("exists") {
    new TestSets {
      val s = union(union(s1, s2), s3)
      assert(exists(s, (y: Int) => y > 2), "Exists 1")
      assert(!exists(s, (y: Int) => y > 3), "Exists 2")
      assert(exists(s, (y: Int) => y == 1), "Exists 3")
    }
  }
  
   test("map") {
    new TestSets {
      val s = map(union(union(s1, s2), s3), x => 2 * x)
      assert(contains(s, 4), "Map 1")
      assert(!contains(s, 1), "Map 2")
      assert(!contains(s, 3), "Map 3")
      assert(forall(s, (y: Int) => y >= 2), "Map 1")
    }
  }  
}
