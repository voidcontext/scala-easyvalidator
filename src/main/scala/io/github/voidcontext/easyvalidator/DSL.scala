package io.github.voidcontext.easyvalidator

package object DSL {

  import scala.language.implicitConversions
  import rule._
  import rule.numeric._
  import rule.string._

  object not extends Negation
  object less extends LessThan
  object greater extends GreaterThan

  case class IntRuleFactory(value: Int) extends NumericRuleFactory[Int](value: Int)
  case class FloatRuleFactory(value: Float) extends NumericRuleFactory[Float](value: Float)
  case class DoubleRuleFactory(value: Double) extends NumericRuleFactory[Double](value: Double)

  implicit def intConv(i: Int) = IntRuleFactory(i)
  implicit def floatConv(f: Float) = FloatRuleFactory(f)
  implicit def doubleConv(d: Double) = DoubleRuleFactory(d)
  implicit def stringConv(s: String) = StringRuleFactory(s)
}
