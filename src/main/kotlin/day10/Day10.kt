package day10

import Day

fun main() = Day10().run()
class Day10 : Day(10) {

    fun getSequence(value: String): String {
        var i = 0
        val result = StringBuffer()

        while (i < value.length) {
            val current = value[i]
            var count = 1
            var nextPtr = i + 1

            while (nextPtr < value.length) {
                if (value[nextPtr] != current)
                    break
                count++
                nextPtr++
            }
            result.append("$count$current")
            i += count
        }

        return result.toString()
    }


    override fun partOne(data: String): String {
        var result = data
        for (i in 0..<40) {
            result = getSequence(result)
        }
        return result.length.toString()
    }


    override fun partTwo(data: String): String {
        var result = data
        for (i in 0..<50) {
            result = getSequence(result)
        }
        return result.length.toString()
    }

}