package io.github.voidcontext.easyvalidator

package object DSL {

  import scala.language.implicitConversions
  import scala.util.{Try, Success, Failure}

  import rule._
  import validator._

  object greater extends NumericRuleFactory {
    def getRule[T <% Ordered[T]](value: T, validator: Validator[T]) = new GreaterThanRule[T](value, validator)
  }

  case class IntValidator(value: Int) extends NumericValidator[Int](value: Int)
  case class FloatValidator(value: Float) extends NumericValidator[Float](value: Float)
  case class DoubleValidator(value: Double) extends NumericValidator[Double](value: Double)

  implicit def intConv(i: Int): IntValidator = new IntValidator(i)
  implicit def floatConv(f: Float): FloatValidator = new FloatValidator(f)
  implicit def doubleConv(d: Double): DoubleValidator = new DoubleValidator(d)
}
