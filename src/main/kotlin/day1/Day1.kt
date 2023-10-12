package day1

import Day

fun main() = Day1().run()

class Day1 : Day(1) {
    override fun partOne(data: String): String {
        var floor = 0
        data.toCharArray().forEachIndexed { i, c ->
            when (c) {
                '(' -> floor += 1
                ')' -> floor -= 1
                else -> {}
            }
        }

        return floor.toString()
    }

    override fun partTwo(data: String): String {
        var floor = 0
        data.toCharArray().forEachIndexed { i, c ->
            when (c) {
                '(' -> floor += 1
                ')' -> floor -= 1
                else -> {}
            }
            if (floor == -1)
                return (i + 1).toString()

        }
        throw IndexOutOfBoundsException()
    }

}