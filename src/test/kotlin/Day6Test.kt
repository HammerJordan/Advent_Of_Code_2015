import day6.Day6
import org.junit.Ignore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day6Test {

    private val solution = Day6()

    @ParameterizedTest
    @CsvSource(
        value = [
            "1000000,'turn on 0,0 through 999,999'",
            "1000,'toggle 0,0 through 999,0'",
            "999996,'turn on 0,0 through 999,999\nturn off 499,499 through 500,500'",
        ]
    )
    fun partOne(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partOne(argument))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "1,'turn on 0,0 through 0,0'",
            "2000000,'toggle 0,0 through 999,999'",
        ]
    )
    fun partTwo(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partTwo(argument))
    }
}