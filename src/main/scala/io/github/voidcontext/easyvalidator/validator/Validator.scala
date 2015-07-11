package io.github.voidcontext.easyvalidator.validator

import io.github.voidcontext.easyvalidator.rule.Rule
import scala.util.{Try, Success, Failure}

abstract class Validator[T](value: T) {
  def validate(validation: () => Boolean, errorMessage: String): Try[T] =  validation() match {
    case true => Success(value)
    case false => Failure(new Exception(errorMessage))
  }
}
