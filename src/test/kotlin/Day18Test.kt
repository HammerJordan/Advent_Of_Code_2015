
import day18.Day18
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day18Test {

    private val solution = Day18()

    val input = """.#.#.#
...##.
#....#
..#...
#.#..#
####..
    """.trimMargin()
    val steps = listOf(
        """..##..
..##.#
...##.
......
#.....
#.##..""",
        """..###.
......
..###.
......
.#....
.#....""",
        """...#..
......
...#..
..##..
......
......""",
        """......
......
..##..
..##..
......
......"""

    )


    @Test
    fun partOneExample() {
        var grid = solution.parseInput(input)
        println("Initial")
        grid.printGrid()

        for (i in steps.indices){
            println("Step: ${i + 1}}")
            grid = solution.toggleLights(grid)
            grid.printGrid()
            val compare = solution.parseInput(steps[i])
            Assertions.assertEquals(compare.grid, grid.grid)
        }












    }
//
//    @Test
//    fun partTwo() {
//        val renderer = solution.parseReindeer(data)
//        val result = solution.calculateScore(renderer, 1000)
//
//        Assertions.assertEquals(312, result["Comet"])
//        Assertions.assertEquals(689, result["Dancer"])
//    }

//    @Disabled
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