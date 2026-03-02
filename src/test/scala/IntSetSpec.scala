import org.scalatest.funsuite.AnyFunSuite

class IntSetSpec extends AnyFunSuite {

  test("add single element and contains it") {
    val s = new IntSet
    s.add(5)
    assert(s.contains(5))
  }

  test("contains returns false for missing element") {
    val s = new IntSet
    s.add(5)
    assert(!s.contains(3))
  }

  test("add multiple elements in order") {
    val s = new IntSet
    s.add(1); s.add(2); s.add(3)
    assert(s.contains(1))
    assert(s.contains(2))
    assert(s.contains(3))
  }

  test("add multiple elements out of order") {
    val s = new IntSet
    s.add(3); s.add(1); s.add(2)
    assert(s.contains(1))
    assert(s.contains(2))
    assert(s.contains(3))
  }

  test("add duplicate element does not change set") {
    val s = new IntSet
    s.add(5); s.add(5)
    assert(s.size == 1)
    assert(s.contains(5))
  }

  test("add negative numbers") {
    val s = new IntSet
    s.add(-3); s.add(0); s.add(3)
    assert(s.contains(-3))
    assert(s.contains(0))
    assert(s.contains(3))
  }

  test("empty set has size 0") {
    val s = new IntSet
    assert(s.size == 0)
  }

  test("size increases with add") {
    val s = new IntSet
    s.add(1); assert(s.size == 1)
    s.add(2); assert(s.size == 2)
    s.add(3); assert(s.size == 3)
  }

  test("size does not increase with duplicate add") {
    val s = new IntSet
    s.add(1); s.add(1); s.add(1)
    assert(s.size == 1)
  }


  test("any returns an element in the set") {
    val s = new IntSet
    s.add(7); s.add(3); s.add(5)
    val e = s.any
    assert(s.contains(e))
  }

  test("any on empty set throws exception") {
    val s = new IntSet
    assertThrows[IllegalArgumentException] {
      s.any
    }
  }

  test("empty set toString") {
    val s = new IntSet
    assert(s.toString == "{}")
  }

  test("single element toString") {
    val s = new IntSet
    s.add(42)
    assert(s.toString == "{42}")
  }

  test("multiple elements toString in sorted order") {
    val s = new IntSet
    s.add(3); s.add(1); s.add(2)
    assert(s.toString == "{1, 2, 3}")
  }

  test("remove existing element returns true") {
    val s = new IntSet
    s.add(1); s.add(2); s.add(3)
    assert(s.remove(2) == true)
    assert(!s.contains(2))
    assert(s.size == 2)
  }

  test("remove non-existing element returns false") {
    val s = new IntSet
    s.add(1); s.add(3)
    assert(s.remove(2) == false)
    assert(s.size == 2)
  }

  test("remove from empty set returns false") {
    val s = new IntSet
    assert(s.remove(1) == false)
  }

  test("remove first element") {
    val s = new IntSet
    s.add(1); s.add(2); s.add(3)
    assert(s.remove(1) == true)
    assert(!s.contains(1))
    assert(s.contains(2))
    assert(s.contains(3))
  }

  test("remove last element") {
    val s = new IntSet
    s.add(1); s.add(2); s.add(3)
    assert(s.remove(3) == true)
    assert(!s.contains(3))
    assert(s.size == 2)
  }

  test("remove only element leaves empty set") {
    val s = new IntSet
    s.add(5)
    assert(s.remove(5) == true)
    assert(s.size == 0)
    assert(!s.contains(5))
  }

  test("two empty sets are equal") {
    val s1 = new IntSet
    val s2 = new IntSet
    assert(s1 == s2)
  }

  test("equal sets with same elements") {
    val s1 = IntSet(1, 2, 3)
    val s2 = IntSet(3, 1, 2)
    assert(s1 == s2)
  }

  test("unequal sets with different elements") {
    val s1 = IntSet(1, 2, 3)
    val s2 = IntSet(1, 2, 4)
    assert(s1 != s2)
  }

  test("unequal sets with different sizes") {
    val s1 = IntSet(1, 2)
    val s2 = IntSet(1, 2, 3)
    assert(s1 != s2)
  }

  test("equals with non-IntSet returns false") {
    val s = IntSet(1, 2, 3)
    assert(!s.equals("hello"))
    assert(!s.equals(42))
  }

  test("empty set is subset of any set") {
    val s1 = new IntSet
    val s2 = IntSet(1, 2, 3)
    assert(s1.subsetOf(s2))
  }

  test("empty set is subset of empty set") {
    val s1 = new IntSet
    val s2 = new IntSet
    assert(s1.subsetOf(s2))
  }

  test("set is subset of itself") {
    val s = IntSet(1, 2, 3)
    assert(s.subsetOf(s))
  }

  test("proper subset") {
    val s1 = IntSet(1, 3)
    val s2 = IntSet(1, 2, 3, 4)
    assert(s1.subsetOf(s2))
  }

  test("not a subset") {
    val s1 = IntSet(1, 5)
    val s2 = IntSet(1, 2, 3)
    assert(!s1.subsetOf(s2))
  }

  test("superset is not a subset") {
    val s1 = IntSet(1, 2, 3)
    val s2 = IntSet(1, 2)
    assert(!s1.subsetOf(s2))
  }

  test("IntSet factory creates correct set") {
    val s = IntSet(5, 3, 1, 4, 2)
    assert(s.size == 5)
    assert(s.contains(1))
    assert(s.contains(2))
    assert(s.contains(3))
    assert(s.contains(4))
    assert(s.contains(5))
  }

  test("IntSet factory with duplicates") {
    val s = IntSet(1, 2, 2, 3, 3, 3)
    assert(s.size == 3)
  }
}
