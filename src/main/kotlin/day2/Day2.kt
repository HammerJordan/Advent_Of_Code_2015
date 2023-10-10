package day2

import Solution

class Day2 : Solution {
    private fun getDimensions(line: String): List<Int> {
        return line
            .split("x")
            .map { x -> x.toInt() }
    }

    override fun partOne(data: String): String {
        return data
            .lines()
            .sumOf { line ->
                val (l, w, h) = getDimensions(line)
                val sides = arrayOf(l * w, w * h, l * h)
                val area = sides.fold(0) { acc, i -> acc + i * 2 }

                area + sides.min()
            }
            .toString()
    }


    override fun partTwo(data: String): String {
        return data
            .lines()
            .sumOf { line ->
                val sides = getDimensions(line)
                val smallest = sides.sorted().take(2)
                val ribbonFeet = smallest.fold(0) { acc, i -> acc + i * 2 }
                val bowFeet = sides.fold(1) { acc, i -> acc * i }
                ribbonFeet + bowFeet
            }
            .toString()
    }

}