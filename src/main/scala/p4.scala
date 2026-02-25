//sbt "runMain p4"
object p4 {
    /*
    Q1 
    */

    trait Shape

    class Rectangle(var width: Double, var height: Double) extends Shape{
        def isSquare: Boolean = width == height
        def stat: String = 
            if (isSquare) s"Square = $width" 
            else s"Rectangle = $width * $height"
    }

    class Ellipse(var semimajor: Double, var semiminor: Double) extends Shape{
        def isCircle: Boolean = semimajor == semiminor
        def stat: String = 
            if (isCircle) s"Circle = $semimajor"
            else s"Ellipse = $semimajor * $semiminor"
    }

    object Rectangle{
        def square (length: Double) = new Rectangle(length, length)
    }

    object Ellipse{
        def circle (radius: Double) = new Ellipse(radius, radius)
    }

    def main(args: Array[String]): Unit = {
        var square = Rectangle.square(5)
        var circle = Ellipse.circle(5)
        var rectangle = new Rectangle(5, 10)
        var ellipse = new Ellipse(5, 10)
        println(square.stat)
        println(circle.stat)
        println(rectangle.stat)
        println(ellipse.stat)
        square.width = 10
        println(square.stat)
        circle.semimajor = 10
        println(circle.stat)
        rectangle.width = 10
        println(rectangle.stat)
        ellipse.semimajor = 10
        println(ellipse.stat)
    }
    /* 
    Square = 5.0
    Circle = 5.0
    Rectangle = 5.0 * 10.0
    Ellipse = 5.0 * 10.0
    Rectangle = 10.0 * 5.0
    Ellipse = 10.0 * 5.0
    Square = 10.0
    Circle = 10.0 

    seperate class corresponding to each shape is not the best design choice as a rectangle can become a square and an ellipse can become a circle. 
    My implementation is a better approach as it is more flexible and allows for the shape to change.
    */

    /*
    Q2
    class Triangle 
    class OpaqueTriangle extends Triangle
    class Renderer {
        def accept(a: Triangle) = println("Accepted for rendering")
        }
    class RayTracingRenderer extends Renderer {
        def accept(a: OpaqueTriangle) = println("Accepted for ray-tracing")
    }
    val a: OpaqueTriangle = new OpaqueTriangle
    val r1: Renderer = new RayTracingRenderer
    r1.accept(a)
    val r2: RayTracingRenderer = new RayTracingRenderer
    r2.accept(a)

    method is overloaded, not overridden

    r1 static type = Renderer
    compiler sees accept(a: Triangle), OpaqueTriangle is a Triangle. So calls base method and returns rendering

    r2 static type = RayTracingRenderer
    compiler sees both methods, so choose specific method. Calls accept(a: OpaqueTriangle) and returns ray-tracing

    change overloaded method to overridden method

    class RayTracingRenderer extends Renderer {
        override def accept(a: Triangle) = println("Accepted for ray-tracing")
    }
    */

    /*
    Q3
    */

}
