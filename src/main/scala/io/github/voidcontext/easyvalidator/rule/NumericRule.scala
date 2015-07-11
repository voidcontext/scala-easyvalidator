package io.github.voidcontext.easyvalidator.rule

import io.github.voidcontext.easyvalidator.validator._
import scala.util.Try

abstract class NumericRule[T](value: T, validator: Validator[T]) extends Rule(value, validator) {
  def than(other: T): Try[T]
}

class GreaterThanRule[T <% Ordered[T]](value: T, validator: Validator[T]) extends NumericRule[T](value, validator) {
  def than(other: T) = validate(() =>  value > other, s"$value is not greater than $other")
}

trait NumericRuleFactory {
  def getRule[T <% Ordered[T]](compareTo: T, validator: Validator[T]): NumericRule[T]
}
