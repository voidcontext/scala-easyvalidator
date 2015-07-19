package io.github.voidcontext.easyvalidator.rule
package numeric

import scala.util.{Try ,Failure}

abstract class NumericRule[T](value: T) extends Rule(value) {
  def than(other: T): Try[T] = Failure(new Exception("Invalid usage of rule"))
  def equal(to: T): Try[T] = Failure(new Exception("Invalid usage of rule"))
}

class EqualsNumericRule[T <% Ordered[T]](value: T) extends NumericRule[T](value) {
  def compareTo(to: T) = validate(() => value == to, s"$value is not equal to $to")
}

class NotEqualsNumericRule[T <% Ordered[T]](value: T) extends NumericRule[T](value) {
  override def equal(to: T) = validate(() => value != to, s"$value is equal to $to")
}

class LessThanRule[T <% Ordered[T]](value: T) extends NumericRule[T](value) {
  override def than(other: T) = validate(() =>  value < other, s"$value is not less than $other")
}

class GreaterThanRule[T <% Ordered[T]](value: T) extends NumericRule[T](value) {
  override def than(other: T) = validate(() =>  value > other, s"$value is not greater than $other")
}




