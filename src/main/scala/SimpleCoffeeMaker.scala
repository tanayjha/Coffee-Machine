import scala.collection.mutable

/**
 * A simple coffee machine which tries preparing beverages in a round robin fashion and outputs those that can be prepared
 */
class SimpleCoffeeMaker extends CoffeeMaker {

  private var ingredientsRemaining = mutable.Map[String, Int]()

  override def prepareCoffee(noOfOutlets: MachineCount, totalItemsQuantity: Ingredients, allBeverages: Beverages): Unit = {
    println("Preparing simple coffee!")
    val listOfBeveragesWithIngredients = List(("hot_tea", allBeverages.hot_tea), ("hot_coffee", allBeverages.hot_coffee),
      ("black_tea", allBeverages.black_tea), ("green_tea", allBeverages.green_tea))
    setTotalIngredients(totalItemsQuantity)
    listOfBeveragesWithIngredients.foreach(beverageWithIngredient =>
      canPrepareBeverage(beverageWithIngredient._1, beverageWithIngredient._2))
  }

  private def setTotalIngredients(allIngredients: Ingredients): Unit = {
    ingredientsRemaining("hot_water") = allIngredients.hot_water.getOrElse(0)
    ingredientsRemaining("hot_milk") = allIngredients.hot_milk.getOrElse(0)
    ingredientsRemaining("ginger_syrup") = allIngredients.ginger_syrup.getOrElse(0)
    ingredientsRemaining("sugar_syrup") = allIngredients.sugar_syrup.getOrElse(0)
    ingredientsRemaining("tea_leaves_syrup") = allIngredients.tea_leaves_syrup.getOrElse(0)
  }

  private def canPrepareBeverage(beverageName: String, beverageIngredients: Ingredients): Unit = {
    val listOfBeverageWithIngredients = List(("hot_water", beverageIngredients.hot_water),
      ("hot_milk", beverageIngredients.hot_milk), ("ginger_syrup", beverageIngredients.ginger_syrup),
      ("sugar_syrup", beverageIngredients.sugar_syrup), ("tea_leaves_syrup", beverageIngredients.tea_leaves_syrup))

    val ingredientsRemainingLocal = ingredientsRemaining.clone()

    listOfBeverageWithIngredients.foreach(beverageIngredients =>
      if (!sufficientQuantity(beverageIngredients._1, beverageIngredients._2, ingredientsRemainingLocal)) {
        println(s"$beverageName cannot be prepared because ${beverageIngredients._1} is not sufficient")
        return
      })
    ingredientsRemaining = ingredientsRemainingLocal
    println(s"$beverageName is prepared")
  }

  // Visible for testing
  def sufficientQuantity(item: String, quantityNeededOpt: Option[Int], ingredientsRemainingLocal: mutable.Map[String, Int]): Boolean = {
    quantityNeededOpt.forall(quantityNeeded => {
      if (quantityNeeded <= ingredientsRemainingLocal(item)) {
        ingredientsRemainingLocal(item) -= quantityNeeded
        true
      } else {
        false
      }
    })
  }
}
