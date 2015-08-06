package io.github.voidcontext.easyvalidator

import DSL._
import org.scalatest._
import scala.util.{Try}

class DSLSpec extends FlatSpec with TryValues {

  def testValidValue[T](result: Try[T], num: T) = assert(result.success.value == num)
  def testInvalidValue[T](result: Try[T], errMsg: String) = assert(
    result.failure.exception.getMessage == errMsg
  )

  val intVal = 10
  val floatVal = 11.3f
  val doubleVal = 11.3
  val stringVal = "foo bar"

  "`greater than` expression" should "validate int values" in {
    testValidValue[Int]     (intVal is greater than  5, intVal)
    testInvalidValue[Int]   (intVal is greater than 19, "10 is not greater than 19")
  }

  it should "validate float values" in {
    testValidValue[Float]   (floatVal is greater than    5, floatVal)
    testInvalidValue[Float] (floatVal is greater than 11.5f, "11.3 is not greater than 11.5")
  }

  it should "validate double values" in {
    testValidValue[Double]   (doubleVal is greater than    5, doubleVal)
    testInvalidValue[Double] (doubleVal is greater than 11.5, "11.3 is not greater than 11.5")
  }

  "`less than` expression" should "validate int values" in {
    testValidValue[Int]     (intVal is less than 11, intVal)
    testInvalidValue[Int]   (intVal is less than 5, "10 is not less than 5")
  }

  it should "validate float values" in {
    testValidValue[Float]   (floatVal is less than 11.300001f, floatVal)
    testInvalidValue[Float] (floatVal is less than 11.2f, "11.3 is not less than 11.2")
  }

  it should "validate double values" in {
    testValidValue[Double]   (doubleVal is less than 15, doubleVal)
    testInvalidValue[Double] (doubleVal is less than 3.45, "11.3 is not less than 3.45")
  }

  "`is` expression" should "validate int values" in {
    testValidValue[Int]     (intVal is 10, intVal)
    testInvalidValue[Int]   (intVal is 5, "10 is not equal to 5")
  }

  it should "validate float values" in {
    testValidValue[Float]   (floatVal is 11.3f, floatVal)
    testInvalidValue[Float] (floatVal is 11.2f, "11.3 is not equal to 11.2")
  }

  it should "validate double values" in {
    testValidValue[Double]   (doubleVal is 11.3, doubleVal)
    testInvalidValue[Double] (doubleVal is 3.45, "11.3 is not equal to 3.45")
  }

  "`is not` expression" should "validate int values" in {
    testValidValue[Int]     (intVal isNot 5, intVal)
    testInvalidValue[Int]   (intVal isNot 10, "10 is equal to 10")
  }

  it should "validate float values" in {
    testValidValue[Float]   (floatVal isNot 11.299f, floatVal)
    testInvalidValue[Float] (floatVal isNot 11.3f, "11.3 is equal to 11.3")
  }

  it should "validate double values" in {
    testValidValue[Double]   (doubleVal isNot 11.301, doubleVal)
    testInvalidValue[Double] (doubleVal isNot 11.3, "11.3 is equal to 11.3")
  }

  "`is shorter than`" should "validate strings" in {
    testValidValue[String](stringVal is longer than 3, stringVal)
    testInvalidValue[String](stringVal is longer than 7, s"$stringVal's length is not greater than 7")
  }

  "`is longer than`" should "validate strings" in {
    testValidValue[String](stringVal is shorter than 10, stringVal)
    testInvalidValue[String](stringVal is shorter than 7, s"$stringVal's length is not less than 7")
  }

  "`matches`" should "validate strings" in {
    testValidValue[String](stringVal matches """foo.*""".r, stringVal)
    testInvalidValue[String](stringVal matches """bar.*""".r, s"$stringVal doesn't match bar.*")
  }
}

