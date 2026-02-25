object p4 {
    trait Shape

    class Rectangle(var width: Double, var height: Double) extends Shape{
        def isSquare: Boolean = width == height
        def stat: String = 
            if (isSquare) s"Square = $width" 
            else s"Rectangle = $width * $height"
    }

    class Ellipse(var semimajor: Double, var semiminor: Double) extends Shape{
        def isCircle: Boolean = semimajor == semiminor
        def stat: String = s"Ellipse = $semimajor * $semiminor"
    }

    object Rectangle{
        def square (length: Double) = new Rectangle(length, length)
    }

    object Ellipse{
        def circle (radius: Double) = new Ellipse(radius, radius)
    }

    def main(args: Array[String]): Unit = {
        val r = Rectangle.square(5)
        val e = Ellipse.circle(5)
        println(r.stat)
        println(e.stat)
    }
}
