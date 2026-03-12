object p6 {

  /*
    Q1
    if N is small, unordered linked list with O(N) is fast, easy to maintain, and memory efficient. Each buket has 0/1 key inside. No need to change
    if N is large, and most keys in one bucket, could consider improving with another data structure but could come at the cost of more variables and more rules to maintain.

   */

  /*
    Q2
    each item going in bucket follows a Bernoulli distribution with probability p = 1/N. Have n items, so
    Total falling in bucket i would follows the Binomial distribution B(n, p) with mean n/N
    Expected number of comparisons of keys that are performed when get is called when the search is
    a) successful = remaining (n-1)/N keys in the bucket. Half of them so (n-1)/2N, key found so + 1. Hence (n-1)/2N + 1
    b) unsuccessful = go through everything = n/N
    
   */

  /*
    Q3
    DTI:
        1. table size = MAX
        2. unique words (key)
        3.if looking for word i and know that it exists, all values from table(i) to the table(j)=word must not be null

    class BagOfWords(val MAX: Int){
        //WF = word+frequency
        private class WF(val word: String, var cnt: Int)
        private val table = new Array[WF](MAX)
        def hash_mod(word: String): Int = {
            math.abs(word.hashCode) % MAX
        }
        def add(word: String): Unit = {
            var h = hash_mod(word)
            var startIdx = h
            while(table(h) != null) {
                if(table(h).word == word) {
                    table(h).cnt += 1
                    return
                }
                h = (h + 1) % MAX
                if(h == startIdx) throw new Exception("Table is full")
            }
            table(h) = new WF(word, 1)
        }
        def findCnt(word: String) : Int = {
            var h = hash_mod(word)
            var startIdx = h
            while(table(h) != null) {
                if(table(h).word == word) {
                    return table(h).cnt
                }
                h = (h + 1) % MAX
                if(h == startIdx) throw new Exception("Table is full")
            }
            0
        }

        def delete(word: String): Unit = {
            var h = hash_mod(word)
            var startIdx = h
            while(table(h) != null) {
                if(table(h).word == word) {
                    if (table(h).cnt > 0) table(h).cnt -= 1
                    return
                }
                h = (h + 1) % MAX
                if(h == startIdx) throw new Exception("Table is full")
            }
        }


        
    }
   */

  /*
    Q4
    class Tree(var word: String, var left: Tree, var right: Tree)

    def Tree(word: String, left: Tree, right:Tree) = new Tree(word, left, right)
    
    //recursive
    def printTreeR(t:Tree, depth: Int): Unit = {
       val indent = " "*depth
       if (node == null) println(indent + "null")
       else {
        println(indent + node.word)
        printTreeR(node.left, depth + 1)
        printTreeR(node.right, depth + 1)
       }
    }
    printTree(t, 0)

    import scala.collection.mutable.Stack
    def printTreeI(t: Tree): Unit = {
        val stack = Stack[(Tree, Int)]() //Node, Depth
        stack.push((t,0))
        while (stack.nonEmpty) {
            val (node, depth) = stack.pop()
            val indent = " " * depth
            if (node == null) println(indent + "null")
            else {
                println(indent + node.word)
                stack.push((node.right, depth + 1))
                stack.push((node.left, depth + 1))
            }
        }
    }

   */

  /*
    
    Q5
    case class Tree(var word: String, var left: Tree, var right: Tree)

    def flip(t: Tree) : Unit = {
        if (t != null){
            val tmp = t.left
            t.left = t.right
            t.right = tmp
        }
        flip(t.left)
        flip(t.right)
    }

   */

  /*
    Q6

    a) recursive

    def sizeR(t: Tree): Int = {
        if (t == null) 0
        else t.count + sizeR(t.left) + sizeR(t.right)
    }

    b)

    def sizeI(t: Tree): Int = {
        var cnt = 0
        val stack = Stack[Tree]()
        if (t != null) stack.push(t)
        //Invariant = total count = current cnt + sum of cnt in stack, where cnt >=0.
        //variant = number of nodes in the stack because always remove something and add children back, so size is decreasing.
        while (stack.nonEmpty) {
            val node = stack.pop()
            cnt += node.count
            if (node.left != null) stack.push(node.left)
            if (node.right != null) stack.push(node.right)
        }
        cnt
    }

   */

  /*
    Q7
    a)

    anagram = permutation of the same combo of characters
    
    import scala.collection.mutable.Set
    def Permutation(word: String): Set[String] = {
        val p = Set[String]()
        if (word.length == 1) {
            p.add(word)
            return p
        }
        for (i <- 0 until word.length) {
            val c = word.charAt(i)
            val sub = word.substring(0, i) + word.substring(i + 1)
            val pRem = Permutation(sub)
            for (s <- pRem) {
                p.add(c + s)
            }
        }
        p
    }
    def anagram(word: String, dict: Set[String]): Set[String] = {
        permutation(word).filter(dict.contains)
    }
    
    code is slow because permutation has complexitiy O(n!)
    
    b)
    anagrammatical dictionary = alphabetically sorted dictionary of anagrams
    
    val anagramDict: Array[(String, String)] = dictionary
      .map(word => (word.sorted, word))
      .sortBy(pair => pair._1)

    def findAnagrams(word: String, dict: Array[(String, String)]): Array[String] = {
        val sortedWord = word.sorted
        dict.filter(pair => pair._1 == sortedWord).map(pair => pair._2)
    }

    val buckets = dictionary.groupBy(word => word.sorted)
    val largestSet = buckets.values.maxBy(bucket => bucket.length) // largest
    
    val buckets = dictionary.groupBy(word => word.sorted)
    val validAnagramBuckets = buckets.filter(bucket => bucket.length > 1)
    val longestAnagrams = validAnagramBuckets.values.maxBy(bucket => bucket(0)length)
    
   */

}
