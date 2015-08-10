package io.github.voidcontext.easyvalidator.rule

import scala.util.{Failure, Success, Try}

trait Predicate
trait ThanPredicate extends Predicate

case class Negation() extends Predicate
case class LessThan() extends ThanPredicate
case class GreaterThan() extends ThanPredicate
case class LongerThan() extends ThanPredicate
case class ShorterThan() extends ThanPredicate
case class Empty() extends Predicate


abstract class Rule[T](value: T) {
  protected def validate(validation: () => Boolean, errorMessage: String): Try[T] = {
    validation() match {
      case true => Success(value)
      case false => Failure(new Exception(errorMessage))
    }
  }
}



