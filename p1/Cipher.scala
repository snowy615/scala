package p1

object Cipher {

  /** Bit-wise exclusive-or of two characters */
  def xor(a: Char, b: Char): Char = (a.toInt ^ b.toInt).toChar

  /** Print ciphertext in octal */
  def showCipher(cipher: Array[Char]) =
    for (c <- cipher) {
      print(c / 64); print(c % 64 / 8); print(c % 8); print(" ")
    }

  /** Read file into array */
  def readFile(fname: String): Array[Char] =
    scala.io.Source.fromFile(fname).toArray

  /** Read from stdin in a similar manner */
  def readStdin() = scala.io.Source.stdin.toArray

  /* ----- Functions below here need to be implemented ----- */

  /** Encrypt plain using key; can also be used for decryption */
  def encrypt(key: Array[Char], plain: Array[Char]): Array[Char] = {
    val keylen = key.length
    val plainlen = plain.length
    var entext = new Array[Char](plainlen)
    var i = 0
    while (i < plainlen) {
      entext(i) = (plain(i).toInt ^ key(i % keylen).toInt).toChar
      i += 1
    }
    entext
  }

  /** Check if the array has repeated characters */
  def isRep(keyChars: Array[Char], j: Int): Boolean = {
    var klen = keyChars.length
    var i = 0
    while (i < klen - j) {
      if (keyChars(i) != keyChars(i + j)) return false
      i += 1
    }
    true
  }

  /** Try to decrypt ciphertext, using crib as a crib */
  def tryCrib(crib: Array[Char], ciphertext: Array[Char]): Unit = {
    val criblen = crib.length
    val cipherlen = ciphertext.length
    var keyChars = new Array[Char](criblen)
    var start = 0
    var i = 0
    while (start < cipherlen - criblen) {
      while (i < criblen) {
        keyChars(i) = (crib(i).toInt ^ ciphertext(i + start).toInt).toChar
        i += 1
      }
      i = 0
      start += 1

      var j = 1
      var keylen = 0
      var f = false
      while (j <= criblen - 2 && !f) {
        if (isRep(keyChars, j)) {
          keylen = j
          f = true
        } else {
          j += 1
        }
      }

      if (f) {
        var key = new Array[Char](keylen)
        i = 0
        while (i < keylen) {
          key((start - 1 + i) % keylen) = keyChars(i)
          i += 1
        }
        println(
          "Found key candidate of length " + keylen + ": " + new String(key)
        )
        print(new String(encrypt(key, ciphertext)))
        return
      }

    }

  }

  /** The first optional statistical test, to guess the length of the key */
  def crackKeyLen(ciphertext: Array[Char]): Unit = ???

  /** The second optional statistical test, to guess characters of the key. */
  def crackKey(klen: Int, ciphertext: Array[Char]): Unit = ???

  /** The main method just selects which piece of functionality to run */
  def main(args: Array[String]) = {
    // string to print if error occurs
    val errString =
      "Usage: scala Cipher (-encrypt|-decrypt) key [file]\n" +
        "     | scala Cipher -crib crib [file]\n" +
        "     | scala Cipher -crackKeyLen [file]\n" +
        "     | scala Cipher -crackKey len [file]"

    // Get the plaintext, either from the file whose name appears in position
    // pos, or from standard input
    def getPlain(pos: Int) =
      if (args.length == pos + 1) readFile(args(pos)) else readStdin()

    // Check there are at least n arguments
    def checkNumArgs(n: Int) =
      if (args.length < n) { println(errString); sys.exit }

    // Parse the arguments, and call the appropriate function
    checkNumArgs(1)
    val command = args(0)
    if (command == "-encrypt" || command == "-decrypt") {
      checkNumArgs(2); val key = args(1).toArray; val plain = getPlain(2)
      print(new String(encrypt(key, plain)))
    } else if (command == "-crib") {
      checkNumArgs(2); val key = args(1).toArray; val plain = getPlain(2)
      tryCrib(key, plain)
    } else if (command == "-crackKeyLen") {
      checkNumArgs(1); val plain = getPlain(1)
      crackKeyLen(plain)
    } else if (command == "-crackKey") {
      checkNumArgs(2); val klen = args(1).toInt; val plain = getPlain(2)
      crackKey(klen, plain)
    } else println(errString)
  }
}
