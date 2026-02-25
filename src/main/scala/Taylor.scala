import scala.math._

object Taylor {
    def main(args: Array[String]): Unit = {
        val mError = 1e-15
        val der = exp(1)+exp(-1)
        
        def fact(n: Int): BigDecimal = {
            if (n == 0) 1
            else BigDecimal(n)*fact(n-1)
        }
        var k = 0
        var f = false
        while (!f) {
            val err = BigDecimal(der)/fact(k+1)
            if (err < mError) {
                f = true
                println(k)
            }
            else k += 1
        }
    }
    
}
