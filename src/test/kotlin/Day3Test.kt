import day2.Day2
import day3.Day3
import org.junit.Ignore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day3Test {

    private val solution = Day3()

    @ParameterizedTest
    @CsvSource(
        value = [
            "2,>",
            "4,^>v<",
            "2,^v^v^v^v^v",
        ]
    )
    fun partOne(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partOne(argument))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "3,^v",
            "3,^>v<",
            "11,^v^v^v^v^v",
        ]
    )
    fun partTwo(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partTwo(argument))
    }
}