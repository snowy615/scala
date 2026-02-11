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

  /*
Q3
test code in p2Spec.scala

running scalaTest:
[info] p2Spec:
[info] The words array
[info] - should sort alphabetically
[info] - should sort by length (short -> long)
[info] - should reverse array
[info] Run completed in 117 milliseconds.
[info] Total number of tests run: 3
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 3, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
   */

  /*
Q4

val timeEnd:Double = 1.0
val numSteps:Int = ...
val timeStep:Double = timeEnd/numSteps
// timeEnd = numSteps * timeStep
var time = 0.0
while (time < timeEnd) {
// Inv: 0 <= time <= timeEnd and time=k*timeStep for some k∈ N
  time += timeStep
}
// Inv => time == timeEnd

a)
when calculating timeStep, timeStep = timeEnd/numSteps. But there will be precision error when storing number. If it is slightly less, then when the while loop condition is theoretically supposed to equal and terminate, it will run one more time because the value is slightly less than timeEnd. (n+1) times could happen rather than n

b)
time inaccuracy.
In the worst case scenerio when we go through the extra iteration in a), time is approximately timeEnd + timeStep. Error is approximately timeStep.

c)
could use subtraction of timeEnd-time < e, where e is a small constant (1e-6) so that rounding errors would be accounted for.
time would be accurate to the precision of double * number of times of iteration, which is very small. Major error earlier was due to extra iteration.
   */

  /*
Q5
   */

//original code
  def search(patt: Array[Char], line: Array[Char]): Boolean = {
    val K = patt.size; val N = line.size
    // I: found = (line[i..i+K] = patt[0..K) for some i in [0..j)) and 0 <= j <= N-K
    var j = 0
    var found = false
    while (j <= N - K && !found) {
      // set found if line[j..j+K] = patt[0..K)
      // I: line[j..j+k) = patt[0..k) for some k in [0..K]
      var k = 0
      while (k < K && line(j + k) == patt(k)) {
        k += 1
      }
      if (k == K) found = true
      j += 1
    }
    // I && (j=N-K+1 || found)
    // found = (line[i..i+K] = patt[0..K) for some i in [0..j)) // j=N-K+1 || found => found = (line[i..i+K] = patt[0..K) for some i in [0..N-K+1))

    found
  }
  /*
5
a)
if found = true, then while loop is skipped, so function always returns found.
test data <- a pattern that isn't in the line
hot in brain should be false but returns true

b)
the pattern is found at the end of the line, so = checks this case, whereas < misses this case.
test data <- a pattern that is only found at the end of the line
rain in brain should be true but returns false

c)
index will be exceeded (out of bounds)
test data <- a pattern that is 1 longer than the line
any eg rain in brain should be true but error

d)
comparing pattern starting at 2nd element rather than first
test data <- first letter different from word, but remaining pattern stays true
vain in brain should be false but return true

e)
compare letter smaller rather or equal than equal
test data <- letter each smaller than the pattern
aaa in bbb should be false but return true

f)
k == K since the loop guarantees that k will be incremented until it is equal to K, k will never be > K
true but just awkward way of writing condition

testing code:
  p2Spec:
[info] The words array
[info] - should sort alphabetically
[info] - should sort by length (short -> long)
[info] - should reverse array
[info] The mistakes
[info] - should a)
[info] - should b)
[info] - should c)
[info] - should d)
[info] - should e)
[info] - should f)
[info] Run completed in 172 milliseconds.
[info] Total number of tests run: 9
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 9, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.

could modify the code above to include the mistakes and then run scalaTest to see error
   */

}
