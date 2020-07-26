
/**
 * Interface which different types of coffee machines can implement. Example:
 * SimpleCoffeeMaker
 * IntelligentCoffeeMaker (selects the beverages to maximise the total count that can be prepared)
 */
trait CoffeeMaker {
  def prepareCoffee(noOfOutlets: MachineCount, totalItemsQuantity: Ingredients, allBeverages: Beverages): Unit
}
