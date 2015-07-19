package io.github.voidcontext.easyvalidator.rule
package string

import scala.util.Try

abstract class StringRule(value: String) extends Rule[String](value) {
}

case class BeginsWithRule(value: String) extends StringRule(value) {
  def test(str: String) = {
    validate(() => value startsWith str, s"$value doesn't begin with '$str'")
  }
}
