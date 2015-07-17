package io.github.voidcontext.easyvalidator.rule

import io.github.voidcontext.easyvalidator.validator._
import scala.util.{Try ,Failure}

abstract class NumericRule[T](value: T, validator: Validator[T]) extends Rule(value, validator) {
  def than(other: T): Try[T] = Failure(new Exception("Invalid usage"))
  def equal(to: T): Try[T] = Failure(new Exception("Invalid usage"))
}

class EqualsNumericRule[T <% Ordered[T]](value: T, validator: Validator[T]) extends NumericRule[T](value, validator) {
  def compareTo(to: T) = validate(() => value == to, s"$value is not equal to $to")
}

class NotEqualsNumericRule[T <% Ordered[T]](value: T, validator: Validator[T]) extends NumericRule[T](value, validator) {
  override def equal(to: T) = validate(() => value != to, s"$value is equal to $to")
}

class LessThanRule[T <% Ordered[T]](value: T, validator: Validator[T]) extends NumericRule[T](value, validator) {
  override def than(other: T) = validate(() =>  value < other, s"$value is not less than $other")
}

class GreaterThanRule[T <% Ordered[T]](value: T, validator: Validator[T]) extends NumericRule[T](value, validator) {
  override def than(other: T) = validate(() =>  value > other, s"$value is not greater than $other")
}




