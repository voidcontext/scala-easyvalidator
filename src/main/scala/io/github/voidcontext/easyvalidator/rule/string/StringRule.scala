package io.github.voidcontext.easyvalidator.rule
package string

import scala.util.Try

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

