
object p5 {
    /*Q1
    a)
    */
    class Node(val datum: Int, var next: Node){
       
        override def toString: String = {
            if (next == null) datum.toString
            else datum + " -> " + next.toString
        }
        
    }

    /*
    b)
    */
    var myList = new Node(1, null)
    for (i <- 2 to 12){
        myList = new Node(i, myList)
    }
    println(myList)

    /*
    c)
    */
    
    var pre: Node = null
    var cur: Node = myList

    //Invariant I = reverse(pre) ++ cur = original list

    while (cur != null){
        val nexttmp = cur.next
        cur.next = pre
        pre = cur
        cur = nexttmp
    }
    myList = pre
    println(myList)


    /* 
    Q2

    def store(name: String, number:String): Unit = {
        val n = find(name)
        if (n.next == null) n.next = new LinkedListHeaderBook.Node(name, number, null)
        else n.next.number = number
    }
     */

    /* 

    Q3

    class Node(val name: String, var number: String, var next: Node)

    //DTI =
        1. head is dummy head and not null
        2. no cycles, will point to null in the end
        3. no duplicates and sorted alphabetically

    //AF = maps name to phone number
    if empty AF(null) = null
    else AF(node) = (node.name, node.number) ++ AF(node.next)
        
    class phonebook { // ordered
        private val head = new Node("", "", null) // dummy head

        //find node right before target
        private def find(target: String): Node = {
            var cur = head
            while (cur.next != null && cur.next.name < target){
                cur = cur.next
            }
            cur
        }

        def store(name: String, number: String): Unit = {
            val pre = find(name)
            if (pre.next == null && pre.next.name != name) pre.next = new Node(name, number, null)
            else pre.next.number = number
        }

        def delete(name: String): Unit = {
            val pre = find(name)
            if (pre.next != null && pre.next.name == name) pre.next = pre.next.next
        }
    }
    
     */

    /* 
    Q4

    implement linked list to be not alphabetically sorted, more like a stack, most recent in front
    class phonebook2 {
        private val head = new Node("", "", null)

        def recall(name: String): String = {
            var pre = head
            while (pre.next != null && pre.next.name != name){
                pre = pre.next
            }
            if (pre.next == null) return null

            val found = pre.next // save
            pre.next = found.next //patch hole
            found.next = head.next //move to front
            head.next = found 
            found.number
        }

    }
     */

    /*
    Q5

    trait Queue[A] {
        def enqueue(x:A): Unit
        def dequeue(): A
        def isEmpty: Boolean
    }
    class ArrayQueue extends Queue[Int]{
        val MAX = 100
        val data = new Array[Int]
        var front = 0
        var size = 0
        //DTI:
            1. 0 <= front < MAX
            2. 0 <= size <= MAX
            3. data != null & data.length == MAX
        //AF: q = [data((front + i)%MAX) | for all 0 <= i < size]
    }
    */


}

//1b loop invariant

//3 if (pre.next == null && pre.next.name != name) pre.next = new Node(name, number, null)
