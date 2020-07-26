import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable

/**
 * Tests for the coffee maker class. Wanted more pure functions for having better tests. Currently adding just one.
 * More refactoring of the class can lead to better test coverage. Trying to stick to the 2.30 hours time limit hence
 * leaving at 1.
 */
class SimpleCoffeeMakerTest extends AnyFlatSpec {
  "A SimpleCoffeeMaker" should "check if sufficient quantity of an item is available or not" in {
    val simpleCoffeeMaker = new SimpleCoffeeMaker
    val testItem = "testItem"
    val ingredientsRemaining = mutable.Map(testItem -> 100)
    assert(simpleCoffeeMaker.sufficientQuantity(testItem, None, ingredientsRemaining.clone()))
    assert(simpleCoffeeMaker.sufficientQuantity(testItem, Some(10), ingredientsRemaining.clone()))
    assert(simpleCoffeeMaker.sufficientQuantity(testItem, Some(100), ingredientsRemaining.clone()))
    assert(!simpleCoffeeMaker.sufficientQuantity(testItem, Some(101), ingredientsRemaining.clone()))
  }
}
