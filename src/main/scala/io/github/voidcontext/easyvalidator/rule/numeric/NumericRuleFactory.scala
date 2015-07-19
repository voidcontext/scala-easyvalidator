package io.github.voidcontext.easyvalidator.rule
package numeric

import scala.util.Try

abstract class NumericRuleFactory[T <% Ordered[T]](value: T) extends RuleFactory[T](value) {

  def is(modifier: Modifier): NumericRule[T]  = modifier match {
    case Negation()    => NotEqualsNumericRule(value)
    case LessThan()    => LessThanRule(value)
    case GreaterThan() => GreaterThanRule(value)
  }

  def is(compareTo: T) = EqualsNumericRule(value).compareTo(compareTo)
}
