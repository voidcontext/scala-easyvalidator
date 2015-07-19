package io.github.voidcontext.easyvalidator.rule
package string

import scala.util.Try

case class StringRuleFactory(value: String) extends RuleFactory[String](value) {
  def beginsWith(str: String): Try[String] = BeginsWithRule(value).test(str) 
}
