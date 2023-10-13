import day10.Day10
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day10Test {

    private val solution = Day10()


    @ParameterizedTest
    @CsvSource(
        value = [
            "11,1",
            "21,11",
            "111221,1211",
            "312211,111221",
        ]
    )
    fun getSequence(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.getSequence(argument))
    }
}