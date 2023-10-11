import day4.Day4
import org.junit.Ignore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day4Test {

    private val solution = Day4()

    @ParameterizedTest
    @CsvSource(
        value = [
            "609043,abcdef",
            "1048970,pqrstuv",
        ]
    )
    fun partOne(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partOne(argument))
    }

    @Ignore
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