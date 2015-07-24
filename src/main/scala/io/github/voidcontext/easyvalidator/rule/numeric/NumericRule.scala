package io.github.voidcontext.easyvalidator.rule
package numeric

import scala.util.{Try ,Failure}

abstract class NumericRule[T](value: T) extends Rule(value) {
  def than(other: T): Try[T] = Failure(new Exception("Invalid usage of rule"))
}

case class EqualsNumericRule[T <% Ordered[T]](value: T) extends NumericRule[T](value) {
  def compareTo(to: T) = validate(() => value == to, s"$value is not equal to $to")
}

case class NotEqualsNumericRule[T <% Ordered[T]](value: T) extends NumericRule[T](value) {
  def compareTo(to: T) = validate(() => value != to, s"$value is equal to $to")
}

case class LessThanRule[T <% Ordered[T]](value: T) extends NumericRule[T](value) {
  override def than(other: T) = validate(() =>  value < other, s"$value is not less than $other")
}

case class GreaterThanRule[T <% Ordered[T]](value: T) extends NumericRule[T](value) {
  override def than(other: T) = validate(() =>  value > other, s"$value is not greater than $other")
}




