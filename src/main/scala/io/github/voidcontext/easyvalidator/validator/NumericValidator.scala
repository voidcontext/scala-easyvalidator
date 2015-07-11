package io.github.voidcontext.easyvalidator.validator

import io.github.voidcontext.easyvalidator.rule.NumericRuleFactory

abstract class NumericValidator[T <% Ordered[T]](value: T) extends Validator[T](value) {
  val self = this

  object is {
    def apply(factory: NumericRuleFactory) = factory.getRule[T](value, self)
  }
}
