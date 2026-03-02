
import org.scalatest.funsuite.AnyFunSuite


class IntSetSpec extends AnyFunSuite{
  var x = 0
  test("x=0"){ 
    x=0
    assert(x===0) 
  }
  test("x=1"){ 
    x=1
    assert(x===1)
  }
}