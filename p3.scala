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






 */

def main(args:Array[String]): Unit = {
  println(sqrt_t_search(10))
  println(sqrt_t_search(100))
  println(sqrt_t_search(1000))
}