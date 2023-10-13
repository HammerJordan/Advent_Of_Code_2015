import day9.Day9
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day9Test {

    private val solution = Day9()


    @Test
    fun parseData() {
        val data = """London to Dublin = 464
            |London to Belfast = 518
            |Dublin to Belfast = 141
        """.trimMargin()
        val routes = solution.parseRoutes(data)

        Assertions.assertEquals(listOf("London","Dublin","Belfast").toString(), routes.cities.toString())
    }


    @Test
    fun partOne() {
        val data = """London to Dublin = 464
            |London to Belfast = 518
            |Dublin to Belfast = 141
        """.trimMargin()

        Assertions.assertEquals("605", solution.partOne(data))
    }

    @Test
    fun partTwo() {
        val data = """London to Dublin = 464
            |London to Belfast = 518
            |Dublin to Belfast = 141
        """.trimMargin()
        Assertions.assertEquals("982", solution.partTwo(data))
    }
}