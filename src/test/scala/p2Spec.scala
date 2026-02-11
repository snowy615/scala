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

}
