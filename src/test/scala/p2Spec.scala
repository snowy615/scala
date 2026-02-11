// In src/test/scala/Sheet2Spec.scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class p2Spec extends AnyFlatSpec with Matchers {

  val words = Array("Zebra", "Apple", "Mango", "Banana", "Cat", "Dog")

  "The words array" should "sort alphabetically" in {
    val result = words.sorted
    val expected = Array("Apple", "Banana", "Cat", "Dog", "Mango", "Zebra")

    // This is the "Test": check if result matches expected
    result should equal(expected)
  }

  // 3. Test 2: Weird Order (Sorting by length)
  it should "sort by length (shortest to longest)" in {
    // .sortBy(_.length) tells Scala to measure the length of each word and sort by that number
    val result = words.sortBy(_.length)

    // We expect "Cat" (3) and "Dog" (3) to be first, and "Banana" (6) to be last.
    // (Note: Since Cat and Dog are same length, their relative order might vary,
    // but they will definitely be before Banana)
    result.head.length should be(3)
    result.last should be("Banana")
  }

  // 4. Test 3: Other Utility (Reverse)
  it should "reverse the list correctly" in {
    val result = words.reverse

    // If we flip the original list, "Dog" becomes the first item
    result.head should be("Dog")
    result.last should be("Zebra")
  }

  // --- TESTS FOR QUESTION 5 ---
  // Helper to make calling your object easier
//   def search(pattern: String, text: String): Boolean = {
//     p2.search(pattern.toCharArray, text.toCharArray)
//   }

//   "The search function" should "find a pattern at the very beginning" in {
//     search("cat", "catamaran") should be(true)
//   }

//   it should "return false if the pattern is not present" in {
//     search("dog", "category") should be(false)
//   }
}
