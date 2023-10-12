import day7.Day7
import java.io.File

const val FILE_PATH = "src/day8.main/kotlin/day7/Data.txt"

fun main() {
    val data = File(FILE_PATH).readText()

    val solution = Day7()

    //val result = solution.partOne(data)
    val result = solution.partTwo(data)

    println(result)

}