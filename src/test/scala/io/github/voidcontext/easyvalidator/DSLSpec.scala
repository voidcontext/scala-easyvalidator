package io.github.voidcontext.easyvalidator

import DSL._
import org.scalatest._
import scala.util.{Try}

class DSLSpec extends FlatSpec with TryValues {

  def testValidNum[T](result: Try[T], num: T) = assert(result.success.value == num)
  def testInvalidNum[T](result: Try[T], errMsg: String) = assert(
    result.failure.exception.getMessage == errMsg
  )

  val intVal = 10
  val floatVal = 11.3f
  val doubleVal = 11.3

  "greater than" should "validate numeric values" in {

    testValidNum[Int]     (intVal is greater than  5, intVal)
    testInvalidNum[Int]   (intVal is greater than 19, "10 is not greater than 19")

    testValidNum[Float]   (floatVal is greater than    5, floatVal)
    testInvalidNum[Float] (floatVal is greater than 11.5f, "11.3 is not greater than 11.5")

    testValidNum[Double]   (doubleVal is greater than    5, doubleVal)
    testInvalidNum[Double] (doubleVal is greater than 11.5, "11.3 is not greater than 11.5")
  }

  "less than" should "validate numeric values" in {
    testValidNum[Int]     (intVal is less than 11, intVal)
    testInvalidNum[Int]   (intVal is less than 5, "10 is not less than 5")

    testValidNum[Float]   (floatVal is less than 11.300001f, floatVal)
    testInvalidNum[Float] (floatVal is less than 11.2f, "11.3 is not less than 11.2")

    testValidNum[Double]   (doubleVal is less than 15, doubleVal)
    testInvalidNum[Double] (doubleVal is less than 3.45, "11.3 is not less than 3.45")
  }

  "is" should "validate numeric values" in {
    testValidNum[Int]     (intVal is 10, intVal)
    testInvalidNum[Int]   (intVal is 5, "10 is not equal to 5")

    testValidNum[Float]   (floatVal is 11.3f, floatVal)
    testInvalidNum[Float] (floatVal is 11.2f, "11.3 is not equal to 11.2")

    testValidNum[Double]   (doubleVal is 11.3, doubleVal)
    testInvalidNum[Double] (doubleVal is 3.45, "11.3 is not equal to 3.45")
  }

  "is not" should "validate numeric values" in {
    testValidNum[Int]     (intVal is not equal 5, intVal)
    testInvalidNum[Int]   (intVal is not equal 10, "10 is equal to 10")

    testValidNum[Float]   (floatVal is not equal 11.299f, floatVal)
    testInvalidNum[Float] (floatVal is not equal 11.3f, "11.3 is equal to 11.3")

    testValidNum[Double]   (doubleVal is not equal 11.301, doubleVal)
    testInvalidNum[Double] (doubleVal is not equal 11.3, "11.3 is equal to 11.3")
  }
}

