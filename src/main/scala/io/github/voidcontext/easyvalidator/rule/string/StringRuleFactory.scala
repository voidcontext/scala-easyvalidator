package io.github.voidcontext.easyvalidator.rule
package string

case class StringRuleFactory(value: String) extends RuleFactory[String](value) {
  def is(predicate: ThanPredicate): ThanableStringRule = predicate match {
    case ShorterThan() => ShorterThanRule(value)
    case LongerThan() => LongerThanRule(value)
  }

  def isNot(predicate: Predicate) = predicate match {
    case Empty() => NotEmptyRule(value).test()
  }

  def matchesRegex(regex: String) = MatchesRules(value).test(regex)
}
