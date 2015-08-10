package io.github.voidcontext.easyvalidator.rule
package string

import scala.util.Try
import scala.util.matching.Regex

abstract class StringRule(value: String) extends Rule[String](value)
abstract class ThanableStringRule(value: String) extends StringRule(value) {
  def than(expected: Int): Try[String]
}

case class ShorterThanRule(value: String) extends ThanableStringRule(value) {
  def than(expectedLength: Int) = validate(() => value.length < expectedLength, s"$value's length is not less than $expectedLength")
}

case class LongerThanRule(value: String) extends ThanableStringRule(value) {
  def than(expectedLength: Int) = validate(() => value.length > expectedLength, s"$value's length is not greater than $expectedLength")
}

case class MatchesRules(value: String) extends StringRule(value) {
  def test(regex: String) = {
    validate(() => value.matches(regex), s"$value doesn't match $regex")
  }
}

case class NotEmptyRule(value: String) extends StringRule(value) {
  def test() = {
    validate(() => !("[^\\s]".r findFirstIn value).isEmpty, "String is empty")
  }
}
