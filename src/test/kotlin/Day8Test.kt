import day8.Day8
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day8Test {

    private val solution = Day8()

    @ParameterizedTest
    @CsvSource(
        value = [
            """2,'""'""",
            """5,'"abc"'""",
            """10,'"aaa\"aaa"'""",
            """6,'"\x27"'""",
        ]
    )
    fun countChars(expected: Int, argument: String) {
        Assertions.assertEquals(expected, solution.countCharacters(argument))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            """0,'""'""",
            """3,'"abc"'""",
            """7,'"aaa\"aaa"'""",
            """1,'"\x27"'""",
        ]
    )
    fun countCharsInMemory(expected: Int, argument: String) {
        Assertions.assertEquals(expected, solution.countCharactersInMemory(argument))
    }

    @Test
    fun partOne() {
        val data = """""
            |"abc"
            |"aaa\"aaa"
            |"\x27"
        """.trimMargin()
        Assertions.assertEquals("12", solution.partOne(data))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            """6,'""'""",
            """9,'"abc"'""",
            """16,'"aaa\"aaa"'""",
            """11,'"\x27"'""",
        ]
    )
    fun countEncoded(expected: Int, argument: String) {
        Assertions.assertEquals(expected, solution.countEncoded(argument))
    }

    @Test
    fun partTwo() {
        val data = """""
            |"abc"
            |"aaa\"aaa"
            |"\x27"
        """.trimMargin()
        Assertions.assertEquals("19", solution.partTwo(data))
    }
}