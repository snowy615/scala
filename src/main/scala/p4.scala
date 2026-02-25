//sbt "runMain p4"
object p4 {
  /*
    Q1
   */

  trait Shape

  class Rectangle(var width: Double, var height: Double) extends Shape {
    def isSquare: Boolean = width == height
    def stat: String =
      if (isSquare) s"Square = $width"
      else s"Rectangle = $width * $height"
  }

  class Ellipse(var semimajor: Double, var semiminor: Double) extends Shape {
    def isCircle: Boolean = semimajor == semiminor
    def stat: String =
      if (isCircle) s"Circle = $semimajor"
      else s"Ellipse = $semimajor * $semiminor"
  }

  object Rectangle {
    def square(length: Double) = new Rectangle(length, length)
  }

  object Ellipse {
    def circle(radius: Double) = new Ellipse(radius, radius)
  }

  def main(args: Array[String]): Unit = {
    var square = Rectangle.square(5)
    var circle = Ellipse.circle(5)
    var rectangle = new Rectangle(5, 10)
    var ellipse = new Ellipse(5, 10)
    println(square.stat)
    println(circle.stat)
    println(rectangle.stat)
    println(ellipse.stat)
    square.width = 10
    println(square.stat)
    circle.semimajor = 10
    println(circle.stat)
    rectangle.width = 10
    println(rectangle.stat)
    ellipse.semimajor = 10
    println(ellipse.stat)
  }
  /*
    Square = 5.0
    Circle = 5.0
    Rectangle = 5.0 * 10.0
    Ellipse = 5.0 * 10.0
    Rectangle = 10.0 * 5.0
    Ellipse = 10.0 * 5.0
    Square = 10.0
    Circle = 10.0

    seperate class corresponding to each shape is not the best design choice as a rectangle can become a square and an ellipse can become a circle.
    My implementation is a better approach as it is more flexible and allows for the shape to change.
   */

  /*
    Q2
    class Triangle
    class OpaqueTriangle extends Triangle
    class Renderer {
        def accept(a: Triangle) = println("Accepted for rendering")
        }
    class RayTracingRenderer extends Renderer {
        def accept(a: OpaqueTriangle) = println("Accepted for ray-tracing")
    }
    val a: OpaqueTriangle = new OpaqueTriangle
    val r1: Renderer = new RayTracingRenderer
    r1.accept(a)
    val r2: RayTracingRenderer = new RayTracingRenderer
    r2.accept(a)

    method is overloaded, not overridden

    r1 static type = Renderer
    compiler sees accept(a: Triangle), OpaqueTriangle is a Triangle. So calls base method and returns rendering

    r2 static type = RayTracingRenderer
    compiler sees both methods, so choose specific method. Calls accept(a: OpaqueTriangle) and returns ray-tracing

    change overloaded method to overridden method

    class RayTracingRenderer extends Renderer {
        override def accept(a: Triangle) = println("Accepted for ray-tracing")
    }
   */

  /*
    Q3
   */
  // test code in p4Spec.scala

  /*
    Q4
   */
  /*
    abstraction specification for stack
    use list to store the sequence of data in Stack[A]
    state: s:[A]
    init: s = []
    s_0 = previous state of stack
    ++ = concatenation of list
    
    trait Stack[A] {
        //add value x to stack
        //post: s = [x] ++ s_0
        def push(x: A): Unit


        //remove most recently added value and return it
        //pre: s != []
        //post: s = s_0 tail ^ return head s_0
        def pop(): A


        //test if stack is empty
        //post: return true if s = [], false otherwise
        def isEmpty(): Boolean
    }
   */

  /*
    Q5
   */
  /*
    //state: S:P Int
    //init: S = {}
    trait IntSet {
        //add elem to the set
        //post: S = S_0 U {x}
        def add(x: Int): Unit

        //test if elem is in the set
        //post: return true if x in S, false otherwise
        def contains(x: Int): Boolean

        //remove elem from the set
        //pre: x in S
        //post: S = S_0 \ {x}
        def remove(x: Int): Unit
   
        //return the number of elements in the set
        //post: return |S|
        def size(): Int

    }

    a)
    //define N in the trait
    Data Type Invariant (DTI): for all x in s, 0<=x<N
    for the add, make sure to have a check for the precondition, rejecting addition of invalid elements
    
    b) bitmap implementation (abstraction, DTI, code)
    
   */
  /*
    Implementation of IntSet using boolean array
    arr: Array[Boolean]
    N: Int
    abs(arr) = {x | 0 <= x < N ^ arr(x) == true}
    DTI: for all x in arr, 0<=x<N. a.length == N
    
   */
  class BitMapSet(val N: Int) extends IntSet {
    val arr = new Array[Boolean](N)

    /*
        add elem to set
        pre: 0 <= x < N
        post: arr(x) == true and arr = arr_0 U {x}
     */
    def add(x: Int): Unit = {
      if (x < 0 || x >= N)
        throw new IllegalArgumentException("x must be between 0 and N-1")
      arr(x) = true
    }

    /*
        test if elem is in the set
        pre: 0 <= x < N
        post: return true if x in S, false otherwise
     */
    def contains(x: Int): Boolean = {
      if (x < 0 || x >= N)
        throw new IllegalArgumentException("x must be between 0 and N-1")
      arr(x)
    }

    /*
        remove elem from set
        pre: 0 <= x < N and x in S
        post: arr(x) == false and arr = arr_0 \ {x}
     */
    def remove(x: Int): Unit = {
      if (x < 0 || x >= N)
        throw new IllegalArgumentException("x must be between 0 and N-1")
      arr(x) = false
    }

    /*
        return the number of elements in the set
        post: return |S|
     */
    def size(): Int = {
      arr.count(_ == true)
    }
  }

  /*
    Q6
   */
  /*
    a) bad description because set is unordered collection of distinct elements. What do you mean by first element??? Random? Minimum?

    b) if head = minimum element from the set, then specification:
        pre: S != {}
        post: S = S_0 ^ returns x from min x in S and x not in S_0
        def head: Int

    c) implementation

    def head: Int = {
        val midx = arr.indexOf(true) // find index of smallest element
        if (midx == -1) throw new NoSuchElementException("head of empty set")
        midx
    }

   */

  /*
    Q7
    answer in ArraysBook.scala
   */

  /*
    Q8
    Delete = O(N)
    cannot use the gap fill method to delete stuff in O(1) in Q7, have to shift elements. Tradeoff from O(1) to O(N)
    
    Find = O(log N)
    Binary search is much faster in finding. Improvement from O(N) to O(log N)
    
    Recall = O(log N)
    call find() which is O(log N) and then access the number in O(1)
    
    Store = O(N)
    Find takes O(log N) and then insert takes O(N)
    
    isInBook = O(log N)
    call find() which is O(log N)
   */

  /*
    Q9 multiset
   */

  /** A bag from [0, N) val = number of occurrences state: bag: Array[Int] Int
    * -> Int init: bag = [0, 0, ..., 0]
    */
  trait Bag {
    // add x to the bag
    // pre: 0 <= x < N
    // post: bag(x) = bag_0(x) + 1
    def add(x: Int): Unit
    // return the number of occurrences of x in the bag
    // pre: 0 <= x < N
    // post: return bag(x)
    def count(x: Int): Int

  }

  class ABag(val N: Int) extends Bag {
    val bag = new Array[Int](N) // could make private

    def add(x: Int): Unit = {
      if (x < 0 || x >= N)
        throw new IllegalArgumentException("x must be between 0 and N-1")
      bag(x) += 1
    }
    def count(x: Int): Int = {
      if (x < 0 || x >= N)
        throw new IllegalArgumentException("x must be between 0 and N-1")
      bag(x)
    }
  }
  /*
    Q10
    counting sort with multiset.
    Know it's from 0 to N-1 so create an array of size N. For every number x, increment bag(x). This takes O(N)
    Then iterate through the array and print x for bag(x) times. This takes O(MAX)
    Total time complexity is O(MAX+N)

   */

}
