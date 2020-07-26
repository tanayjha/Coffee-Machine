import java.io.File

import org.json4s.DefaultFormats
import org.json4s.JsonAST.JValue
import org.json4s.native.JsonMethods._

/**
 * Class to extract the beverage and ingredient info from the provided JSON.
 * Ideally it should be generic and beverages/ingredients specific to the JSON should be abstracted out.
 * However due to an issue with Json4s (https://github.com/json4s/json4s/issues/329) I am not able to do that currently.
 * Because of shortage of time keeping it kind of specific right now but should not be too hard to make it generic
 * @param inputJsonFile
 */
class IngredientsLoader(inputJsonFile: File) {

  implicit val formats: DefaultFormats = DefaultFormats

  private val inputJsonString = convertJsonToString(inputJsonFile)
  private val coffeeMachine = jsonStringToJValue(inputJsonString)

  def getTotalItemQuantity: Ingredients = (coffeeMachine \\ "total_items_quantity").extract[Ingredients]

  def getListOfBeverages: Beverages = (coffeeMachine \\ "beverages").extract[Beverages]

  def getTotalOutlets: MachineCount = (coffeeMachine \\ "outlets").extract[MachineCount]

  private def convertJsonToString(inputJson: File): String = {
    val source = scala.io.Source.fromFile(inputJson)
    try source.mkString finally source.close()
  }

  private def jsonStringToJValue(jsonStr: String): JValue = parse(jsonStr)
}

case class MachineCount(count_n: Int)

case class AllBeverages(beverages: Beverages)

case class Beverages(hot_tea: Ingredients, hot_coffee: Ingredients, black_tea: Ingredients, green_tea: Ingredients)

case class Ingredients(hot_water: Option[Int] = None,
                       hot_milk: Option[Int] = None,
                       ginger_syrup: Option[Int] = None,
                       sugar_syrup: Option[Int] = None,
                       tea_leaves_syrup: Option[Int] = None)


// Note: For generic parsing of ingredients. Tried but facing class cast error due to json4s
// bug: https://github.com/json4s/json4s/issues/329

// case class Ingredients(ingredients: Map[String, Int])
