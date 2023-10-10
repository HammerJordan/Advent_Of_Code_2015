import day2.Day2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day2Test {

    private val solution = Day2()

    @ParameterizedTest
    @CsvSource(
        value = [
            "58,2x3x4",
            "43,1x1x10"
        ]
    )
    fun partOne(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partOne(argument))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "34,2x3x4",
            "14,1x1x10"
        ]
    )
    fun partTwo(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partTwo(argument))
    }
}