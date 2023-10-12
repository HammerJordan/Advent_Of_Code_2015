import day5.Day5
import day6.Day6
import java.io.File

const val FILE_PATH = "src/main/kotlin/day6/Data.txt"

fun main() {
    val data = File(FILE_PATH).readText()

    val solution = Day6()


    //val result = solution.partOne(data)
    val result = solution.partTwo(data)

    println(result)

}