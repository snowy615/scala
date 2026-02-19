//Q1
//find index i s.t. a[0..i) < x <= a[i..N). Pre: a is sorted
def search(a: Array[Int], x: Int): Int = {
  val N = a.size
  var i = 0; var j = N
  while (i < j) {
    val m = (i + j) / 2
    if (a(m) < x) i = m + 1 else j = m
  }
  i
}

/* 
a)
if a is not increasing, then binary sort may not work and might return a garbage value

b)
when N=0, j=0 so i<j is false, loop is skipped, returns i=0

c)
if i and j are both very large, (i+j)/2 might overflow as i+j exceeds upper limit. Number will become negative, leading to error when acessing the array by the index.
Could solve by doing i + (j-i)/2 instead 

 */

/*
Q2
 */

def sqrt_t_search(x: Int): Int = {
  require (x >= 0)
  var start = 0; var end = x+1
  if (x==0) return 0
  //I = start^2 <= x < end^2
  while (start + 1 < end) {
    val dif = end-start
    val m1 = start + dif / 3
    val m2 = start + 2*dif / 3
    if (m1*m1 > x) end = m1
    else if (m2*m2 <= x) start = m2
    else {
      start = m1
      end = m2
    }
  }
  start
}

/* 
b) for example, when size of array is 2, will definitely have an empty interval.
start = 0, end = 2
m1 = 0+2/3 = 0
m2 = 0+4/3 = 1
interval [start, m1) = [0,0) = empty

so yes could have empty interval, but will never go into the empty interval as the conditions start + 1 < end (start == end) make sure to exit before that when interval is empty so code is still correct

c)
2^31 -1 = 2147483647 doesn't work because I did +1 so exceed limit of int. so returns 0.

 */

/*
Q3
a) log2(1000) = 9.96, so 10 iterations

b) same code as a except first find max boundary for r. Do this by doubling r until tooBig(r) is true. Then do binary search

c) optmize by using 2^2^0, 2^2^1, 2^2^2, ... until tooBig(r) is true. Then do binary search to find exponent, then do another binary search to find x

 */

def tooBig(y: BigInt): Boolean = {
  val x = 100
  if (x<y) return true
  else return false
}

def findxa():BigInt = {
  //last false = x
  var l = 1
  var r = 1000
  while (l<r)
    var m = l+(r-l+1)/2
    if (tooBig(m)) r = m-1
    else l = m
  l
}

def findxb():BigInt = {
  var r = 1
  while (tooBig(r)) r = r*2
  var l = if (r==1) 1 else r/2
  while (l<r)
    var m = l+(r-l+1)/2
    if (tooBig(m)) r = m-1
    else l = m
  l
}

def findxc():BigInt = {
  var e = 0
  var tmp = 2
  while (!tooBig(tmp)){
    tmp = tmp*tmp
    e += 1
  }
  if (e==0) return 1

  var el = 1 << (e-1)
  var er = 1 << e

  while (el<er){
    var em = el + (er-el)/2
    tmp = 1 << em
    if (tooBig(tmp)) er = em-1
    else el = em
  }
  e = el

  var r = 1<<(e+1)
  var l = 1<<e
  while (l<r)
    var m = l+(r-l+1)/2
    if (tooBig(m)) r = m-1
    else l = m
  l
}


/*
Q4
loop from i=1 to N-1 to ensure a[0..i] is sorted
  find idx where a[idx] = v using binary search
  shift elements from i to idx+1
  a[idx] = v
*/
def binaryInsertSort(a: Array[Int]): Unit = {
  val N = a.length
  var i = 1
  while (i < N) {
    var v = a(i)
    var l = 0
    var r = i
    while (l<r){
      var m = l+(r-l)/2
      if (a(m)<v) l = m+1 else r = m
    }
    var idx = l
    var j = i
    while (j>idx){
      a(j) = a(j-1)
      j -= 1
    }
    a(idx) = v
    i += 1
  }
}


/*
Q5
using the leftmost element as pivot could be bad if the result is not balanced (monotonic decreasing/increasing array). Eg, a decreasing array
T(n) = T(n-1) + O(n) = O(n^2)
the advantage is that it is simple to implement O(1) without having to do a search. Usually it should be balanced since pivot is random
expected size of the larger segment is 3/4n because
  assume uniformly distributed from [0, n], larger piece will be uniformly distributed from [0.5n, n]. So average will be 0.75 n.
T(n) = T(3*n/4) + T(n/4) O(n) = O(nlogn)

*/
//partition a[l..r], return k s.t. a[l..k] < x <= a[k+1..r] and l <= k < r
def partition(l:Int, r:Int, a:Array[Int]):Int = {
  val x = a(l)
  var i = l+1
  var j = r
  while (i < j){
    if (a(i) < x) i += 1
    else {
      val t = a(i)
      a(i) = a(j)
      a(j) = t
      j -= 1
    }
  }
  a(l) = a(i-1)
  a(i-1) = x
  i-1
}

/*
Q6
the element at a(j) could also be larger than the pivot. But it gets moved twice since when I find a larger element, I would swap it, then I would have to swap it back to r-1 spot
improved version is that j only stays at an element smaller than or equal to the pivot to avoid unnecessary swaps
I = a[l+1..i) < x <= a[j..r] && l < i <= j <= r
*/

def partition2(l:Int, r:Int, a:Array[Int]):Int = {
  val x = a(l)
  var i = l+1
  var j = r
  while (i < j){
    while (i < j && a(i) < x) i += 1
    while (i < j && a(j) >= x) j -= 1
    if (i < j){
      val t = a(i)
      a(i) = a(j-1)
      a(j-1) = t
      j -= 1
    }
  }
  a(l) = a(i-1)
  a(i-1) = x
  i-1
}

/*
Q7
*/

/*
Q8
*/

def main(args:Array[String]): Unit = {
  println(sqrt_t_search(10))
  println(sqrt_t_search(100))
  println(sqrt_t_search(1000))
  println(sqrt_t_search(214748364))
}