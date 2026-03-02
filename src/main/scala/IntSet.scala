/*
questions:
  dummy header node?
  Yes to make insertion/deletion of first element easier
  linked list allow repetition?
  no, too difficult to check repetition later on for size
  increasing order?
  yes, searching is easier
  include anything else in the state for efficiency?

task:
   O() time complexity
   invariant
*/


// A class of objects to represent a set

class IntSet{
  // State: S : P(Int) (where "P" represents power set)

  // The following lines just define some aliases, so we can subsequently
  // write "Node" rather than "IntSet.Node".
  private type Node = IntSet.Node
  // Constructor
  private def Node(datum: Int, next: Node) = new IntSet.Node(datum, next)

  // Init: S = {}
  private var theSet : Node = Node(0, null) // dummy header node

  // size of the set, updated with insertion/deletion of elements
  private var sized: Int = 0

  /** Convert the set to a string.
    * O(N) where N = size of set */
  override def toString : String = {
    var s = "{"
    var cur = theSet.next

    //Invariant = s = string representaiton of the set so far
    while (cur != null){
      s += cur.datum.toString
      if (cur.next != null) s += ", "
      cur = cur.next
    }
    s += "}"
    s
  }

  /** Add element e to the set
    * Post: S = S_0 U {e} 
    * O(N) is the worst case where N = size of set*/
  def add(e: Int) : Unit = {
    var pre = theSet
    var cur = theSet.next
    
    //Invariant = since linked list is sorted, everything before cur is smaller than e
    while (cur != null && cur.datum < e){
      pre = cur
      cur = cur.next
    }
    if (cur == null || cur.datum != e){ // check repetition
      pre.next = Node(e, cur)
      sized += 1
    }
  }

  /** Length of the list
    * Post: S = S_0 && returns #S 
    * O(1) because it's updated and stored in variable sized*/
  def size : Int = sized

  /** Does the set contain e?
    * Post: S = S_0 && returns (e in S) 
    * O(N) goes through the list */
  def contains(e: Int) : Boolean = {
    var cur = theSet.next
    //Invariant = everything before cur is smaller than e
    while (cur != null && cur.datum < e){
      cur = cur.next
    }
    if (cur != null && cur.datum == e){
      true
    }
    else{
      false
    }
  }

  /** Return any member of the set.  (This is comparable to the operation
    * "head" on scala.collection.mutable.Set, but we'll use a name that does
    * not suggest a particular order.)
    * Pre: S != {}
    * Post: S = S_0 && returns e s.t. e in S 
    * O(1) returns the first element*/
  def any : Int = {
    require (theSet.next != null)
    theSet.next.datum
  }


  /** Does this equal that?
    * Post: S = S_0 && returns that.S = S 
    * O(N)*/
  override def equals(that: Any) : Boolean = that match {
    case s: IntSet => 
      if (this.size != s.size) return false
      var cur1 = this.theSet.next
      var cur2 = s.theSet.next
      
      // Invariant: all evaluated nodes up to cur1 and cur2 are the same
      while (cur1 != null && cur2 != null) {
        if (cur1.datum != cur2.datum) return false
        cur1 = cur1.next
        cur2 = cur2.next
      }
      true
    case _ => false
  }
    
  /** Remove e from the set; result says whether e was in the set initially
    * Post S = S_0 - {e} && returns (e in S_0) 
    * Complexity: O(N) */
  def remove(e: Int): Boolean = {
    var pre = theSet
    var cur = theSet.next
    
    // Invariant: e is not in the nodes up to pre
    while (cur != null && cur.datum < e) {
      pre = cur
      cur = cur.next
    }
    
    if (cur != null && cur.datum == e) {
      pre.next = cur.next
      sized -= 1
      true
    } else {
      false
    }
  }

    /** Test whether this is a subset of that.
    * Post S = S_0 && returns S subset-of that.S 
    * Complexity: O(N + M) where N,M = size of this, that */
  def subsetOf(that: IntSet): Boolean = {
    var cur1 = this.theSet.next
    var cur2 = that.theSet.next
    
    // Invariant: all nodes before cur1 in this set exist in that set
    while (cur1 != null) {
      while (cur2 != null && cur2.datum < cur1.datum) {
        cur2 = cur2.next
      }
      if (cur2 == null || cur2.datum > cur1.datum) return false
      cur1 = cur1.next
    }
    true
  }

  // ----- optional parts below here -----

  /** return union of this and that.  
    * Post: S = S_0 && returns res s.t. res.S = this U that.S */
  def union(that: IntSet) : IntSet = ???

  /** return intersection of this and that.  
    * Post: S = S_0 && returns res s.t. res.S = this intersect that.S */
  def intersect(that: IntSet) : IntSet = ???

  /** map
    * Post: S = S_0 && returns res s.t. res.S = {f(x) | x <- S} */
  def map(f: Int => Int) : IntSet = ???

  /** filter
    * Post: S = S_0 && returns res s.t. res.S = {x | x <- S && p(x)} */
  def filter(p : Int => Boolean) : IntSet = ???
}


// The companion object
object IntSet{
  /** The type of nodes defined in the linked list */
  private class Node(var datum: Int, var next: Node)

  /** Factory method for sets.
    * This will allow us to write, for example, IntSet(3,5,1) to
    * create a new set containing 3, 5, 1 -- once we have defined 
    * the main constructor and the add operation. 
    * post: returns res s.t. res.S = {x1, x2,...,xn}
    *       where xs = [x1, x2,...,xn ] */
  def apply(xs: Int*) : IntSet = {
    val s = new IntSet; for(x <- xs) s.add(x); s
  }
}
