import java.io.File

abstract class Day(private val day: Int) {
    private val path: String get() = "src/main/kotlin/day$day/Data.txt"
    abstract fun partOne(data: String): String
    abstract fun partTwo(data: String): String

    fun run() {
        val data = File(path).readText()
        println("Part 1:")
        try {
            val result = partOne(data)
            println(result)

        }  catch (e: Error){
            println(e)
        }
        println("-------------------")
        println("Part 2:")
        try {
            val result = partTwo(data)
            println(result)
        }  catch (e: Error){
            println(e)
        }

    }



}