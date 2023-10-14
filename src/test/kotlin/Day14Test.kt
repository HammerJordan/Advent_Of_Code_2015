
import day14.Day14
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day14Test {

    private val solution = Day14()

    private val data = """Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
    |Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
""".trimMargin()


    @Test
    fun partOne() {
        val renderer = solution.parseReindeer(data)
        val result = solution.calculateDistances(renderer, 1000)

        Assertions.assertEquals(1120, result["Comet"])
        Assertions.assertEquals(1056, result["Dancer"])
    }

    @Test
    fun partTwo() {
        val renderer = solution.parseReindeer(data)
        val result = solution.calculateScore(renderer, 1000)

        Assertions.assertEquals(312, result["Comet"])
        Assertions.assertEquals(689, result["Dancer"])
    }

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