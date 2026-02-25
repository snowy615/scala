//sbt "testOnly p4Spec"
import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.HashSet

class p4Spec extends AnyFunSuite {
  test("HashSet test of add, remove, contains, size, and isEmpty") {
    val set = new HashSet[String]()
    assert(set.isEmpty) // check isEmpty
    assert(set.size == 0)

    set.add("a")

    assert(!set.isEmpty)
    assert(set.size == 1) // check size
    assert(set.contains("a")) // check contains
    assert(!set.contains("b"))

    set.add("b")
    set.remove("a") // check remove

    assert(!set.contains("a"))

    val addDup = set.add("b") // check add duplicate element

    assert(set.size == 1)
    assert(addDup == false)

    val removeMis = set.remove("a") // check remove non-existent element

    assert(set.size == 1)
    assert(removeMis == false)

    assert(set.size == 1)
    set.remove("b")
    assert(set.isEmpty)

    set.remove("b")

    val exception = intercept[NoSuchElementException] {
      set.head
    }
    assert(exception.getMessage == "next on empty iterator")
  }

}
