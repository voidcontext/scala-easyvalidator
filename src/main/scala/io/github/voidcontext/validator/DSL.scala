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

  trait NumericComparisonFactory {
    def comparison[T <% Ordered[T]](compareTo: T): Comparison[T]
  }

  object greater extends NumericComparisonFactory {
    def comparison[T <% Ordered[T]](n: T) = new Comparison[T](n) {
      def compare(n: T, m: T) = validate(
          n > m,
          s"$n is not greater than $m"
        )
    }
  }

  abstract sealed class NumericValidator[T <% Ordered[T]](value: T) extends Validator {
    object is {
      def apply(factory: NumericComparisonFactory) = factory.comparison[T](value)
    }
  }

  case class IntValidator(value: Int) extends NumericValidator[Int](value: Int)
  case class FloatValidator(value: Float) extends NumericValidator[Float](value: Float)
  case class DoubleValidator(value: Double) extends NumericValidator[Double](value: Double)

  implicit def intConv(i: Int): IntValidator = new IntValidator(i)
  implicit def floatConv(f: Float): FloatValidator = new FloatValidator(f)
  implicit def doubleConv(d: Double): DoubleValidator = new DoubleValidator(d)
}
