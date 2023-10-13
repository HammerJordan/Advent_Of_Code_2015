import day11.Day11
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day11Test {

    private val solution = Day11()


    @ParameterizedTest
    @CsvSource(
        value = [
            "xx,xy",
            "xy,xz",
            "xz,ya",
            "ya,yb",
        ]
    )
    fun incrementPassword(argument: String, expected: String) {
        Assertions.assertEquals(expected, solution.incrementPassword(argument))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "hijklmmn,false",
            "abbceffg,false",
            "abbcefgjk,false",
            "abcdffaa,true",
            "ghjaabcc,true",
        ]
    )
    fun isPasswordValid(argument: String, expected: Boolean) {
        Assertions.assertEquals(expected, solution.isPasswordValid(argument))
    }


    @Disabled
    @ParameterizedTest
    @CsvSource(
        value = [
            "abcdefgh,abcdffaa",
            "ghijklmn,ghjaabcc",
        ]
    )
    fun partOne(argument: String, expected: String) {
        Assertions.assertEquals(expected, solution.partOne(argument))
    }

    @Disabled
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