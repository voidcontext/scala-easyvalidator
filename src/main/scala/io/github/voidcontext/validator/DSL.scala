package io.github.voidcontext.simplevalidation



package object DSL {

  import scala.language.implicitConversions
  import scala.util.{Try, Success, Failure}

  abstract sealed class Comparison[T](value: T) {

    def compare(n: T, m: T): Try[T]

    def than(m: T) = compare(value, m)

    def validate(result: Boolean, message: String): Try[T] = result match {
      case true  => Success(value)
      case false => Failure(new Exception(message))
    }
  }
  abstract sealed class Validator()

  trait ComparisonFactory[T] {
    def comparison(compareTo: T): Comparison[T]
  }

  object greater extends ComparisonFactory[Int] {
    def comparison(n: Int) = new Comparison[Int](n) {
      def compare(n: Int, m: Int) = validate(
          n > m,
          s"$n is not greater than $m"
        )
    }
  }

  case class IntValidator(value: Int) extends Validator {

    object is {
      def apply(factory: ComparisonFactory[Int]) = factory.comparison(value)
    }
  }



  implicit def intConv(i: Int) = new IntValidator(i)
}
