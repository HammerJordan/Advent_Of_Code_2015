import day5.Day5
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day5Test {

    private val solution = Day5()

    @ParameterizedTest
    @CsvSource(
        value = [
            "1,ugknbfddgicrmopn",
            "1,aaa",
            "0,jchzalrnumimnmhp",
            "0,haegwjzuvuyypxyu",
            "0,dvszwmarrgswjxmb",
        ]
    )
    fun partOne(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partOne(argument))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "1,qjhvhtzxzqqjkmpb",
            "1,xxyxx",
            "0,uurcxstgmygtbstg",
            "0,ieodomkazucvgmuy",
        ]
    )
    fun partTwo(expected: String, argument: String) {
        Assertions.assertEquals(expected, solution.partTwo(argument))
    }
}