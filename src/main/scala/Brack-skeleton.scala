//sbt "runMain Brack -PossibleRec testcase.txt"
//sbt "runMain Brack -NumberRec testcase.txt"
//sbt "runMain Brack -Tabulate testcase.txt"

/** Import is for readLine so that we can write input directly to the program */
import scala.io.StdIn

object Brack {
  // Maximum length of word so we can define our arrays in dynamic programming
  val MAXWORD = 30

  // Operation to take 'A', 'B' and 'C' to corresponding Ints
  def LetterToInt(a: Char): Int = {
    if (a == 'A' || a == 'B' || a == 'C') {
      return (a.toInt - 'A'.toInt);
    } else {
      println("Please only Letters from A,B,C.")
      sys.exit
    }
  }

  // Defining the op array for everything to use
  val op = Array.ofDim[Int](3, 3)
  op(0)(0) = 1; op(0)(1) = 1; op(0)(2) = 0
  op(1)(0) = 2; op(1)(1) = 1; op(1)(2) = 0
  op(2)(0) = 0; op(2)(1) = 2; op(2)(2) = 2

  /** Read file into array (discarding the EOF character) */
  def readFile(fname: String): Array[Char] =
    scala.io.Source.fromFile(fname).toArray.init

  /* Functions below here need to be implemented */

  // TASK 1
  // PossibleRec checks whether bracketing to something is possible recursively
  // Checks whether w(i,j) can be bracketed to z

  def PossibleRec(w: Array[Int], i: Int, j: Int, z: Int): Boolean = {
    if (j - i == 1) w(i) == z // single element
    else {
      var found = false
      for (k <- i + 1 until j; if !found) { // k = split point, w[i..k) and w[k..j)
        for (l <- 0 to 2; if !found) { // l = left
          for (r <- 0 to 2; if !found) { // r = right
            if (
              op(l)(r) == z && PossibleRec(w, i, k, l) && PossibleRec(
                w,
                k,
                j,
                r
              )
            ) found = true // possible
          }
        }
      }
      found
    }
  }
  // telescoping difference O(7^n)

  // TASK 2
  // NumberRec which checks the ways you get a result recursively
  // Computes number of ways w(i,j) can be bracketed to get z

  def NumberRec(w: Array[Int], i: Int, j: Int, z: Int): Int = {
    if (j - i == 1) {
      if (w(i) == z) 1 else 0
    } else {
      var totalWays = 0
      for (k <- i + 1 until j) {
        for (l <- 0 to 2) {
          for (r <- 0 to 2) {
            if (op(l)(r) == z) {
              totalWays += NumberRec(w, i, k, l) * NumberRec(w, k, j, r)
            }
          }
        }
      }
      totalWays
    }
  }

  // TASK 3
  // TODO Runtime analysis of recursive solution along with tests

  /*

  exponential growth, catalan number O(4^n/n^1.5)
  Catalan numbers 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700, 1767263190
  Cn = (2n choose n)/(n+1)

  Bracketing values for ABBA
A can be achieved in 2 ways
B can be achieved in 1 way
C can be achieved in 2 ways
Execution time: 0.0094 s

Bracketing values for ABAABA
A can be achieved in 17 ways
B can be achieved in 10 ways
C can be achieved in 15 ways
Execution time: 0.0174 s

Bracketing values for ABAABAABA
A can be achieved in 550 ways
B can be achieved in 379 ways
C can be achieved in 501 ways
Execution time: 0.2846 s

Bracketing values for ABAABAAABBA
A can be achieved in 6403 ways
B can be achieved in 4595 ways
C can be achieved in 5798 ways
Execution time: 7.1381 s

Bracketing values for AAAAAAAAAAA
A can be achieved in 4161 ways
B can be achieved in 8153 ways
C can be achieved in 4482 ways
Execution time: 11.0659 s


   */

  // Binary tree class
  abstract class BinaryTree
  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree
  case class Leaf(value: Char) extends BinaryTree
  // These arrays should hold the relevant data for dynamic programming
  var poss = Array.ofDim[Boolean](MAXWORD, MAXWORD, 3)
  var ways = Array.ofDim[Int](MAXWORD, MAXWORD, 3)
  // var exp = Array.ofDim[BinaryTree](MAXWORD, MAXWORD, 3)

  // Task 4, 5
  // TODO Fill out arrays with dynamic programming solution
  // array(i)(j)(z), i = start index, j = end index, z = target val

  def Tabulate(w: Array[Int], n: Int): Unit = {
    // L = length of substring
    // L=1
    for (i <- 0 until n) {
      for (z <- 0 to 2) {
        if (w(i) == z) {
          poss(i)(i + 1)(z) = true
          ways(i)(i + 1)(z) = 1
        } else {
          poss(i)(i + 1)(z) = false
          ways(i)(i + 1)(z) = 0
        }
      }
    }

    for (L <- 2 to n) { // L>1
      for (i <- 0 to n - L) { // i = start index
        val j = i + L // j = end index (exclusive)

        for (z <- 0 to 2) { poss(i)(j)(z) = false; ways(i)(j)(z) = 0 }

        for (k <- i + 1 until j) { // k = split point
          for (l <- 0 to 2) {
            for (r <- 0 to 2) {
              if (poss(i)(k)(l) && poss(k)(j)(r)) {
                val z = op(l)(r)
                poss(i)(j)(z) = true
                ways(i)(j)(z) += ways(i)(k)(l) * ways(k)(j)(r)
              }
            }
          }
        }
      }
    }
  }

  // Task 6
  // TODO Runtime analysis of dynamic programming version with tests

  /*
before recursive approach max would be around 10-15 chars and runtime
stuck at 15 chars
stuck at 12 chars
ok at 9 chars
slow at 11 chars
12 is probably max for recursive

defined as 32-bit integer, so for dynamic approach, max is 19 char to avoid int overflow


now much better.
3 nested for loops so expect O(n^3), much better
sbt "runMain Brack -Tabulate testcase.txt"
Bracketing values for ABAABAAABBA
A can be achieved 6403 ways
B can be achieved 4595 ways
C can be achieved 5798 ways
Execution time: 0.0022 s

Bracketing values for ABA
A can be achieved 1 way
B cannot be achieved
C can be achieved 1 way
Execution time: 0.0012 s

Bracketing values for ABAAAABBBBAABABBABAC
A can be achieved 840645886 ways
B can be achieved 429783971 ways
C can be achieved 496833333 ways
Execution time: 0.0036 s

DP over recursive approach!
   */

  // Printing for a binary tree
  // def print_tree(t : BinaryTree): Unit = {
  // 	t match {
  // 		case Leaf(value) => print(value)
  // 		case Node(left, right) =>
  // 			print("(")
  // 			print_tree(left)
  // 			print_tree(right)
  // 			print(")")
  // 	}
  // }

  /** The main method just selects which piece of functionality to run */
  def main(args: Array[String]) = {

    // string to print if error occurs
    val errString =
      "Usage: scala Brack -PossibleRec [file]\n" +
        "     | scala Brack -NumberRec [file]\n" +
        "     | scala Brack -Tabulate [file]\n"

    if (args.length > 2) {
      println(errString)
      sys.exit
    }

    // Get the plaintext, either from the file whose name appears in position
    // pos, or from standard input
    def getPlain(pos: Int) =
      if (args.length == pos + 1) readFile(args(pos))
      else StdIn.readLine.toArray

    // Check there are at least n arguments
    def checkNumArgs(n: Int) =
      if (args.length < n) { println(errString); sys.exit }

    // Parse the arguments, and call the appropriate function
    checkNumArgs(1)
    val plain = getPlain(1)
    val command = args(0)

    // Making sure the letters are of the right type
    val len = plain.length
    var plainInt = new Array[Int](len)
    if (len > MAXWORD) {
      println("Word Too Long! Change MAXWORD")
      sys.exit;
    } else {
      for (i <- 0 until len) {
        plainInt(i) = LetterToInt(plain(i))
      }
    }

    // Executing appropriate command
    if (command == "-PossibleRec") {
      println("Bracketing values for " + plain.mkString(""))
      val t0 = System.nanoTime()
      for (i <- 0 to 2) {
        if (PossibleRec(plainInt, 0, len, i)) {
          println(('A'.toInt + i).toChar + " is Possible");
        } else {
          println(('A'.toInt + i).toChar + " is not Possible");
        }
      }
      val t1 = System.nanoTime()
      printf("Execution time: %.4f s\n", (t1 - t0) / 1e9)
    } else if (command == "-NumberRec") {
      var z: Int = 0
      println("Bracketing values for " + plain.mkString(""))
      val t0 = System.nanoTime()
      for (i <- 0 to 2) {
        z = NumberRec(plainInt, 0, len, i)
        if (z == 1) {
          printf(('A'.toInt + i).toChar + " can be achieved in %d way\n", z)
        } else {
          printf(('A'.toInt + i).toChar + " can be achieved in %d ways\n", z)
        }
      }
      val t1 = System.nanoTime()
      printf("Execution time: %.4f s\n", (t1 - t0) / 1e9)
    } else if (command == "-Tabulate") {
      val t0 = System.nanoTime()
      Tabulate(plainInt, len)
      val t1 = System.nanoTime()
      println("Bracketing values for " + plain.mkString(""))
      for (v <- 0 to 2) {
        var z: Int = ways(0)(len)(v)
        if (z == 0) {
          println(('A'.toInt + v).toChar + " cannot be achieved")
        } else if (z == 1) {
          printf(('A'.toInt + v).toChar + " can be achieved %d way\n", z)
          // printf("For example:")
          // print_tree(exp(0)(len)(v))
          // printf("\n")
        } else if (z > 1) {
          printf(('A'.toInt + v).toChar + " can be achieved %d ways\n", z)
          // printf("For example:")
          // print_tree(exp(0)(len)(v))
          // printf("\n")
        }
      }
      printf("Execution time: %.4f s\n", (t1 - t0) / 1e9)
    } else println(errString)
  }
}
