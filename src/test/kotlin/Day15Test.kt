import day15.Day15
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day15Test {

    private val solution = Day15()

    val data = """Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8 
Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"""

    @Test
    fun parseData() {
        val ingredients = solution.parseIngredients(data)

        Assertions.assertEquals(2, ingredients.count())
        Assertions.assertEquals("Butterscotch", ingredients.first().name)
    }


    @Test
    fun partOne() {
        Assertions.assertEquals("62842880", solution.partOne(data))
    }
//
//    @Test
//    fun partTwo() {
//        val renderer = solution.parseReindeer(data)
//        val result = solution.calculateScore(renderer, 1000)
//
//        Assertions.assertEquals(312, result["Comet"])
//        Assertions.assertEquals(689, result["Dancer"])
//    }

//    @Disabled
//    @ParameterizedTest
//    @CsvSource(
//        value = [
//            "1,qjhvhtzxzqqjkmpb",
//            "1,xxyxx",
//            "0,uurcxstgmygtbstg",
//            "0,ieodomkazucvgmuy",
//        ]
//    )
//    fun partTwo(expected: String, argument: String) {
//        Assertions.assertEquals(expected, solution.partTwo(argument))
//    }
}