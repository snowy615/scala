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

  // original code
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

  /*
Q6
   */

  // looking for smallest n s.t. s[0..N-n) = s[n..N)
  // outer loop invariant I = does not recur with period k
  //inner loop invariant I = s[i] == s[i+n]
  def period(s: Array[Char]): Int = {
    val n = s.length
    var l = 1 // possible length of period
    while (l < n) {
      var i = 0
      var f = true // flag for matches
      while (i < n - l && f) {
        if (s(i) != s(i + l)) f = false // mismatch
        i += 1
      }
      if (f) return l // return smallest period
      l += 1
    }
    n // none found, so period = length of string
  }
  /*
[info] - should =1 aaaa
[info] - should =2 abab
[info] - should =4 abac
[info] Run completed in 201 milliseconds.
[info] Total number of tests run: 12
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 12, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
   */

  /*
Q7
iterate through elements, if p(i) true, then return true. Else continue. If after checking the list, flag is still false, then return false

invariant
I = for all k in [0,i), p(k) is false

before loop
i = 0, so I = true

during loop
if p(i) is true then f = true. loop terminates with I = true
if p(i) is false then i increments. I = true

termination
variant = N - i, since i increments, variant decreases. Will terminate.

after loop
if f = true then exists true (justification in during loop)
if i = N then for all k in [0,N), p(k) is false, so I=true
   */
  def exists(p: Int => Boolean, N: Int): Boolean = {
    var i = 0
    var f = false
    while (i < N && !f) {
      if (p(i)) f = true
      i += 1
    }
    f
  }

  /*
Q8
a)
find smallest m in 1/m <= p/q
approach 1: binary search to find m
approach 2: take reciprocal of p/q.
m >= q/p
so m = ceil(q/p)

val m = (q.toDouble/p).ceil.toInt
or
val m = (q+p-1)/p since p,q are positive integers

b)
code below (function = findMs)

c)
in the loop,
pp = pp*m - qq

m is chosen such that 1/m <= p/q < 1/(m-1)
1/(m-1) is the upper bound because if it is possible, then m-1 would have been chosen

so from p/q < 1/(m-1),
pm-q<p
same form as p being updated, so new pp will be less than the orginal pp. since pp is a positive finite int and decreasing, loop must terminate when pp = 0

d)
show array is strictly increasing
the algorithm of finding m is greedy, always picking the smallest possible m such that 1/m is maximized.
So it will be increasing.
It is strict it will always take the largest possible value for m, so it will never be greater than 1/2 of something left for a duplicate denominator to show up
   */

  def findMs(p: Int, q: Int): Array[Int] = {
    var pp = p; var qq = q // change to mutable variables
    var d = new Array[Int](200) // denominator array
    var i = 0 // index for ms
    while (pp > 0 && i < 200) {
      val m = (qq + pp - 1) / pp
      d(i) = m // find m and update pp, qq
      pp = pp * m - qq
      qq = qq * m
      i += 1
    }
    d.take(i) // take the denominators
  }

  /*
Q9
linear search for exponent
   */
  def log3(n: BigInt): Int = {
    var x = 0 // exp count
    var p = BigInt(1) // power of 3
    while (p * 3 <= n) { // floor
      p *= 3
      x += 1
    }
    x // return exp = log3 x
  }

  /*
Q10
code eval below
   */
  def eval(a: Array[Double], x: Double): Double = {
    var i = 0
    var n = a.length
    var xp = 1.0 // power of x ^ i
    var sum = 0.0 // sum of a(i)*x^i
    while (i < n) {
      sum += a(i) * xp
      xp *= x
      i += 1
    }
    sum
  }

}


// to be corrected
//3
 //desc order can be sortwith (<)
  //.filter(_>'s)
//4c change to double to have more precision, steps = best
// scala test could use assert statements
//assert(search("word1", "word2"))
//5e index out of bounds

// 6 invariant S[0..N-k) = S[k..N) ^ 1 <= k <= N
// return if the invariant is true rather than if statement j == N-k

// 7 need boundary for invariant
// I ^ (n=N or P(n))

//8 d difficult


//p1
// 1b) positive , negative res + 3

