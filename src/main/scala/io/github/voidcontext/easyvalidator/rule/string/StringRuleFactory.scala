package io.github.voidcontext.easyvalidator.rule
package string

import scala.util.Try

case class StringRuleFactory(value: String) extends RuleFactory[String](value) {
  def is(modifier: ThanModifier): ThanableStringRule = modifier match {
    case ShorterThan() => ShorterThanRule(value)
    case LongerThan() => LongerThanRule(value)
  }
}
