import org.scalatest.funsuite.AnyFunSuite

class p2Spec extends AnyFunSuite {

  // Q3
  val words = Array("abcdefg", "cdefg", "bcdefg", "efg", "defg")

  test("sort alphabetically") {
    val result = words.sorted
    val expected = Array("abcdefg", "bcdefg", "cdefg", "defg", "efg")
    assert(result sameElements expected)
  }

  test("sort by length (short -> long)") {
    val result = words.sortBy(_.length)
    val expected = Array("efg", "defg", "cdefg", "bcdefg", "abcdefg")
    assert(result sameElements expected)
  }

  test("reverse array") {
    val result = words.reverse
    val expected = Array("defg", "efg", "bcdefg", "cdefg", "abcdefg")
    assert(result sameElements expected)
  }

  // Q5

  // helper function to convert string to array of char
  def search(patt: String, line: String): Boolean = {
    p2.search(patt.toCharArray, line.toCharArray)
  }

  test("search: hot not in brain") {
    assert(search("hot", "brain") === false)
  }
  test("search: rain in brain") {
    assert(search("rain", "brain") === true)
  }
  test("search: rain in brain (duplicate)") {
    assert(search("rain", "brain") === true)
  }
  test("search: vain not in brain") {
    assert(search("vain", "brain") === false)
  }
  test("search: aaa not in bbb") {
    assert(search("aaa", "bbb") === false)
  }
  test("search: aaa in aaa") {
    assert(search("aaa", "aaa") === true)
  }

  // Q6
  test("period = 1 for aaaa") {
    assert(p2.period("aaaa".toCharArray) === 1)
  }
  test("period = 2 for abab") {
    assert(p2.period("abab".toCharArray) === 2)
  }
  test("period = 4 for abac") {
    assert(p2.period("abac".toCharArray) === 4)
  }
}
