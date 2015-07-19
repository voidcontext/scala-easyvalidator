package io.github.voidcontext.easyvalidator.rule

import scala.util.{Failure, Success, Try}

trait Modifier
case class Negation() extends Modifier
case class LessThan() extends Modifier
case class GreaterThan() extends Modifier


abstract class Rule[T](value: T) {
  protected def validate(validation: () => Boolean, errorMessage: String): Try[T] = {
    validation() match {
      case true => Success(value)
      case false => Failure(new Exception(errorMessage))
    }
  }
}



