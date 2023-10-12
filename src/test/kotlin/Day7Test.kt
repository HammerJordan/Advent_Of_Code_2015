import day7.Day7
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day7Test {

    private val solution = Day7()
    private val puzzle = """123 -> x
456 -> y
x AND y -> d
x OR y -> e
x LSHIFT 2 -> f
y RSHIFT 2 -> g
NOT x -> h
NOT y -> i"""
    private val expected = """d: 72
e: 507
f: 492
g: 114
h: 65412
i: 65079
x: 123
y: 456"""

    @Test
    fun partOne(){
        val result = solution.partOne(puzzle)
        println(result)

        Assertions.assertEquals(expected, result)
    }


//    @ParameterizedTest
//    @CsvSource(
//        value = [
//            "1,ugknbfddgicrmopn",
//            "1,aaa",
//            "0,jchzalrnumimnmhp",
//            "0,haegwjzuvuyypxyu",
//            "0,dvszwmarrgswjxmb",
//        ]
//    )
//    fun partOne(expected: String, argument: String) {
//        Assertions.assertEquals(expected, solution.partOne(argument))
//    }
//
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