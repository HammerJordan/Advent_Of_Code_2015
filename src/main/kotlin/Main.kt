import day2.Day2
import day3.Day3
import java.io.File

const val FILE_PATH = "src/main/kotlin/day3/Data.txt"

fun main() {
    val data = File(FILE_PATH).readText()

    val solution = Day3()


    //val result = solution.partOne(data)
    val result = solution.partTwo(data)

    println(result)

}