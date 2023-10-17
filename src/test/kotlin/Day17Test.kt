
import day17.Day17
import org.junit.jupiter.api.Test

class Day17Test {

    private val solution = Day17()

    val input = """20
        |15
        |10
        |5
        |5
    """.trimMargin()

    @Test
    fun getCombinations(){
        val containers = solution.parseInput(input)
        val combinations = solution.getCombinations(containers,25)
        for (c in combinations)
            println(c)
    }


//    @Test
//    fun partOne() {
//        Assertions.assertEquals("62842880", solution.partOne(data))
//    }
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