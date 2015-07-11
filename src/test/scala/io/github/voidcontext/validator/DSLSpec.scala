package io.github.voidcontext.validator

import DSL._
import org.scalatest._
import scala.util.{Try, Success, Failure}

class DSLSpec extends FlatSpec with TryValues {
  it should "validate an int" in {

    val validresult = 10 is greater than 5

    assert(validresult.success.value == 10)

    val invalidresult = 10 is greater than 19

    assert(invalidresult.failure.exception.getMessage == "10 is not greater than 19")
  }
}

