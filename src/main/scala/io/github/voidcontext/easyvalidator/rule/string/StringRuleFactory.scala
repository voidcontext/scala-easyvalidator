package io.github.voidcontext.easyvalidator.rule
package string

case class StringRuleFactory(value: String) extends RuleFactory[String](value) {
  def is(modifier: ThanModifier): ThanableStringRule = modifier match {
    case ShorterThan() => ShorterThanRule(value)
    case LongerThan() => LongerThanRule(value)
  }

  def isNot(modifier: Modifier) = modifier match {
    case Empty() => NotEmptyRule(value).test()
  }

  def matchesRegex(regex: String) = MatchesRules(value).test(regex)
}
