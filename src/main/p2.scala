object p2 {
/*
Q1
a)
def swap(x: Int, y: Int) = { val t = x; x = y; y = t }
this code will have compile error because x and y are immutable and cannot be reassigned 
b)
def swapEntries(a: Array[Int], i: Int, j: Int) = { val t = a(i); a(i) = a(j); a(j) = t }
this code will work because it is an array and arrays are mutable. A(i) will be swapped with element in A(j)
*/

/*
Q2
object SideEffect {
  var x = 3
  var y = 5
  def nasty (x: Int) : Int = { y = 1; 2*x}
  def main(args: Array[String]) = println(nasty(x)+y)

}

will print 7 because nasty(x) will return 2*3 = 6 and y = 1 so 6+1 = 7
y+nasty(x) will print 11 because y was originally 5, then nasty is called, returning 2*3=6. 5+6 = 11
*/

/* */
/* */
}