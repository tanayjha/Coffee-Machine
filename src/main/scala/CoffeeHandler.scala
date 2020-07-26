import java.io.File

/**
 * Main coffee handler class which has the ingredientLoader and the coffeeMaker and co-ordinates the preparation of the
 * coffee. Currently the implementation of the loader and the maker are directly added in this class. However we can easily
 * invert the dependency using a framework like spring by injecting the specific implementations here and only using the interfaces
 * in this class.
 */

object CoffeeHandler extends App {

  override def main(args: Array[String]): Unit = {
    val inputJsonFile = new File("/Users/tanay/git/Coffee Machine/src/main/resources/sample1.json")
    val ingredientsLoader = new IngredientsLoader(inputJsonFile)

    val noOfOutlets = ingredientsLoader.getTotalOutlets
    val totalItemsQuantity = ingredientsLoader.getTotalItemQuantity
    val allBeverages = ingredientsLoader.getListOfBeverages

    val coffeeMaker = new SimpleCoffeeMaker
    coffeeMaker.prepareCoffee(noOfOutlets, totalItemsQuantity, allBeverages)
  }
}
