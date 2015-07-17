package io.github.voidcontext.easyvalidator.validator

import io.github.voidcontext.easyvalidator.rule._
import scala.util.Try

abstract class NumericValidator[T <% Ordered[T]](value: T) extends Validator[T](value) {
  val self = this

  def is(modifier: Modifier): NumericRule[T]  = modifier match {
    case Negation()    => new NotEqualsNumericRule(value, self)
    case LessThan()    => new LessThanRule(value, self)
    case GreaterThan() => new GreaterThanRule(value, self)
  }

  def is(compareTo: T) = {
    val rule = new EqualsNumericRule(value, self)
    rule.compareTo(compareTo)
  }

}
