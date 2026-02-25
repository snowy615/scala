// Representing the phone book using a pair of arrays

object ArraysBook extends Book {
  private val MAX = 1000 // max number of names we can store
  private val names = new Array[String](MAX)
  private val numbers = new Array[String](MAX)
  private var count = 0
  // These variables together represent the mapping
  // Abs: book =
  // { names(i) -> numbers(i) | i <- [0..count) }
  // DTI: 0 <= count <= MAX &&
  // entries in names[0..count) are distinct

  // Return the index i<count s.t. names(i) = name; or
  //              return count if no such index exists
  private def find(name: String): Int = {
    // Invariant: name not in names[0..i) && i <= count
    var i = 0
    while (i < count && names(i) != name) i += 1
    i
  }

  /** Return the number stored against name */
  def recall(name: String): String = {
    val idx = find(name)
    assert(idx < count && names(idx) == name)
    numbers(idx)
  }

  /** Is name in the book? */
  def isInBook(name: String): Boolean = {
    val idx = find(name)
    idx < count && names(idx) == name
  }

  /** Add the maplet name -> number to the mapping */
  def store(name: String, number: String): Unit = {
    val idx = find(name)
    if (idx < count && names(idx) == name) { // name exist and update number
      numbers(idx) = number 
    } else { // name not exist, shift elements to create space at idx, then add
      var j = count
      while (j > idx) {
        names(j) = names(j-1)
        numbers(j) = numbers(j-1)
        j -= 1
      }
      names(idx) = name
      numbers(idx) = number
      count += 1
    }
  }

  /** Delete the number stored against the name (if it exists) post: initial
    * state:(name in starting book domain) ^ state change: (name exists, then
    * new is the book removed else new is the same as original) return would be
    * the initial state and the state change
    *
    * formal: post: returns (name in dom book_0) ^ ((name in dom book_0 ^ book =
    * book_0 \ {name}) or (name not in dom book_0 ^ book = book_0))
    */

  def delete(name: String): Boolean = {
    val i = find(name)
    if (i == count) false
    else {
      // delete at index idx, and then shift all elements back to fill the gap
      var j = i
      count -= 1
      while (j<count){
        names(j) = names(j+1)
        numbers(j) = numbers(j+1)
        j += 1
      }
      true
    }
  }
}

//ordered
object OrderedArraysBook extends Book {
  private val MAX = 1000 // max number of names we can store
  private val names = new Array[String](MAX)
  private val numbers = new Array[String](MAX)
  private var count = 0
  // These variables together represent the mapping
  // Abs: book =
  // { names(i) -> numbers(i) | i <- [0..count) }
  // DTI: 0 <= count <= MAX &&
  // entries in names[0..count) are distinct

  // Return the index i<count s.t. names(i) = name; or
  //              return count if no such index exists
  private def find(name: String): Int = {
    // Invariant: name not in names[0..i) && i <= count
    var l = 0
    var r = count - 1
    while (l <= r) {
      val m = (l+r)/2
      if (names(m) == name) return m
      else if (names(m) < name) l = m + 1
      else r = m - 1
    }
    l // not found so insert at l
  }

  /** Return the number stored against name */
  def recall(name: String): String = {
    val i = find(name)
    assert(i < count)
    numbers(i)
  }

  /** Is name in the book? */
  def isInBook(name: String): Boolean = find(name) < count

  /** Add the maplet name -> number to the mapping */
  def store(name: String, number: String): Unit = {
    val i = find(name)
    if (i == count) {
      assert(count < MAX); names(i) = name; count += 1
    }
    numbers(i) = number
  }

  /** Delete the number stored against the name (if it exists) post: initial
    * state:(name in starting book domain) ^ state change: (name exists, then
    * new is the book removed else new is the same as original) return would be
    * the initial state and the state change
    *
    * formal: post: returns (name in dom book_0) ^ ((name in dom book_0 ^ book =
    * book_0 \ {name}) or (name not in dom book_0 ^ book = book_0))
    */

  def delete(name: String): Boolean = {
    val i = find(name)
    if (i == count) false
    else {
      // plug the gap with the final entry becaause ordering does not matter
      count -= 1
      names(i) = names(count)
      numbers(i) = numbers(count)
      true
    }
  }
}
