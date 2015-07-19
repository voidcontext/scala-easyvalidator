package io.github.voidcontext.easyvalidator.rule
package numeric

import scala.util.Try

abstract class NumericRuleFactory[T <% Ordered[T]](value: T) extends RuleFactory[T](value) {

  def is(modifier: Modifier): NumericRule[T]  = modifier match {
    case Negation()    => new NotEqualsNumericRule(value)
    case LessThan()    => new LessThanRule(value)
    case GreaterThan() => new GreaterThanRule(value)
  }

  def is(compareTo: T) = {
    val rule = new EqualsNumericRule(value)
    rule.compareTo(compareTo)
  }

}
