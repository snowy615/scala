// In src/test/scala/Sheet2Spec.scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class p2t extends AnyFlatSpec with Matchers {

  // --- TESTS FOR QUESTION 3 ---
  "A String Array" should "sort alphabetically by default" in {
    val fruits =
      Array("Kiwi", "Apple", "Banana", "Elderberry", "Cherry", "Date")
    fruits.sorted should equal(
      Array("Apple", "Banana", "Cherry", "Date", "Elderberry", "Kiwi")
    )
  }

  it should "sort by length using sortBy" in {
    val fruits =
      Array("Kiwi", "Apple", "Banana", "Elderberry", "Cherry", "Date")
    val sortedByLength = fruits.sortBy(_.length)
    // Check that lengths are increasing
    sortedByLength.map(_.length) shouldBe sortedByLength.map(_.length).sorted
  }

  // --- TESTS FOR QUESTION 5 ---
  // Helper to make calling your object easier
  def search(pattern: String, text: String): Boolean = {
    p2.search(pattern.toCharArray, text.toCharArray)
  }

  "The search function" should "find a pattern at the very beginning" in {
    search("cat", "catamaran") should be(true)
  }

  it should "return false if the pattern is not present" in {
    search("dog", "category") should be(false)
  }
}
