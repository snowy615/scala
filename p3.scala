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
*/

/*
Q6
*/

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