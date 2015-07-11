package io.github.voidcontext.simplevalidation

import DSL._
import org.scalatest._
import scala.util.{Try}

class DSLSpec extends FlatSpec with TryValues {

  def testValidNum[T](result: Try[T], num: T) = assert(result.success.value == num)
  def testInvalidNum[T](result: Try[T], errMsg: String) = assert(
    result.failure.exception.getMessage == errMsg
  )

  "greater than" should "validate an numerics" in {

    val intVal = 10
    testValidNum[Int]     (intVal is greater than  5, intVal)
    testInvalidNum[Int]   (intVal is greater than 19, "10 is not greater than 19")

    val floatVal = 11.3f
    testValidNum[Float]   (floatVal is greater than    5, floatVal)
    testInvalidNum[Float] (floatVal is greater than 11.5f, "11.3 is not greater than 11.5")

    val doubleVal = 11.3
    testValidNum[Double]   (doubleVal is greater than    5, doubleVal)
    testInvalidNum[Double] (doubleVal is greater than 11.5, "11.3 is not greater than 11.5")
  }
}

