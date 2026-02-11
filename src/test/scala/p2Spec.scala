import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class p2Spec extends AnyFlatSpec with Matchers {

  // Q3
  val words = Array("abcdefg", "cdefg", "bcdefg", "efg", "defg")

  "The words array" should "sort alphabetically" in {
    val result = words.sorted
    val expected = Array("abcdefg", "bcdefg", "cdefg", "defg", "efg")
    result should equal(expected)
  }

  it should "sort by length (short -> long)" in {
    val result = words.sortBy(_.length)
    val expected = Array("efg", "defg", "cdefg", "bcdefg", "abcdefg")
    result should equal(expected)
  }

  it should "reverse array" in {
    val result = words.reverse
    val expected = Array("defg", "efg", "bcdefg", "cdefg", "abcdefg")
    result should equal(expected)
  }

  // Q5

  // helper function to convert string to array of char
  def search(patt: String, line: String): Boolean = {
    p2.search(patt.toCharArray, line.toCharArray)
  }

  "The mistakes " should "a) " in {
    search("hot", "brain") should be(false)
  }
  it should "b) " in {
    search("rain", "brain") should be(true)
  }
  it should "c) " in {
    search("rain", "brain") should be(true)
  }
  it should "d) " in {
    search("vain", "brain") should be(false)
  }
  it should "e) " in {
    search("aaa", "bbb") should be(false)
  }
  it should "f) " in {
    search("aaa", "aaa") should be(true)
  }

  // Q6
  "Period" should "=1 aaaa" in {
    p2.period("aaaa".toCharArray) should be(1)
  }
  it should "=2 abab" in {
    p2.period("abab".toCharArray) should be(2)
  }
  it should "=4 abac" in {
    p2.period("abac".toCharArray) should be(4)
  }
}
