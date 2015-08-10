package io.github.voidcontext.easyvalidator.rule

import scala.util.{Failure, Success, Try}

trait Modifier
trait ThanModifier extends Modifier 

case class Negation() extends Modifier
case class LessThan() extends ThanModifier
case class GreaterThan() extends ThanModifier
case class LongerThan() extends ThanModifier
case class ShorterThan() extends ThanModifier
case class Empty() extends Modifier


abstract class Rule[T](value: T) {
  protected def validate(validation: () => Boolean, errorMessage: String): Try[T] = {
    validation() match {
      case true => Success(value)
      case false => Failure(new Exception(errorMessage))
    }
  }
}



