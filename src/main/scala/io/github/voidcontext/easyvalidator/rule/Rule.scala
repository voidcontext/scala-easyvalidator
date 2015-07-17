package io.github.voidcontext.easyvalidator.rule

import io.github.voidcontext.easyvalidator.validator.Validator
import scala.util.{Failure, Success, Try}

trait Modifier
case class Negation() extends Modifier
case class LessThan() extends Modifier
case class GreaterThan() extends Modifier


abstract class Rule[T](value: T, validator: Validator[T]) {
  protected def validate(validation: () => Boolean, errorMessage: String): Try[T] = validator.validate(validation, errorMessage)
}



