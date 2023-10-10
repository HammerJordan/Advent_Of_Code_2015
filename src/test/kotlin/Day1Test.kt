import day1.Day1
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day1Test {

    private val solution = Day1()

    @ParameterizedTest
    @CsvSource(
        value = [
            "0,(())",
            "0,()()",
            "3,(((",
            "3,(()(()(",
            "3,))(((((",
            "-1,())",
            "-1,))(",
            "-3,)))",
            "-3,)())())",
        ]
    )
    fun partOne(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partOne(argument))
    }

    @ParameterizedTest
    @CsvSource(
        value = ["1,)",
            "5,()())"]
    )
    fun partTwo(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partTwo(argument))
    }
}