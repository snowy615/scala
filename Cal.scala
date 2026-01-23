def square(n: Int): Int = {
  n * n
}

def rem3(n: Int): Int = {
  n - (n / 3) * 3
}

def ps(n: Int): Int = {
  var i = 0
  while (i * i <= n) {
    i += 1
  }
  (i - 1) * (i - 1)
}

def fib(n: Int): Int = {
  if (n == 0) 0
  else if (n == 1) 1
  else fib(n - 1) + fib(n - 2)
}

@main def run(): Unit = {
  println(square(5))
  println(rem3(5))
  println(ps(5))
  println(fib(10))

}
