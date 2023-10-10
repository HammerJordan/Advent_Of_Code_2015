import day2.Day2
import java.io.File

const val FILE_PATH = "src/main/kotlin/day2/Data.txt"

fun main() {
    val data = File(FILE_PATH).readText()

    val solution = Day2()


    //val result = solution.partOne(data)
    val result = solution.partTwo(data)

    println(result)

}