package io.github.voidcontext.easyvalidator.rule
package numeric

import scala.util.Try

abstract class NumericRuleFactory[T <% Ordered[T]](value: T) extends RuleFactory[T](value) {

  def is(predicate: ThanPredicate): ThanableNumericRule[T]  = predicate match {
    case LessThan()    => LessThanRule(value)
    case GreaterThan() => GreaterThanRule(value)
  }

  def is(compareTo: T) = EqualsNumericRule(value).compareTo(compareTo)
  def isNot(compareTo: T) = NotEqualsNumericRule(value).compareTo(compareTo)
}
