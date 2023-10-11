import day4.Day4
import java.io.File

const val FILE_PATH = "src/main/kotlin/day4/Data.txt"

fun main() {
    val data = File(FILE_PATH).readText()

    val solution = Day4()


    //val result = solution.partOne(data)
    val result = solution.partTwo(data)

    println(result)

}