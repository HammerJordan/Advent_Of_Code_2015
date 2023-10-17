package day17

import Day
import util.combinations

fun main() = Day17().run()
class Day17 : Day(17) {


    fun parseInput(input: String): List<Int> {
        return input
            .lines()
            .map { it.toInt() }

    }

    //[20, 15, 10, 5, 5]
    fun getCombinations(containers: List<Int>, size: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        for (i in containers.indices) {
            for (combination in containers.combinations(i)) {
                if (combination.sum() == size)
                    result.add(combination.toList())
            }
        }

        return result
    }


    override fun partOne(data: String): String {
        val containers = parseInput(data)
        val results = getCombinations(containers, 150)
        return results.count().toString()
    }


    override fun partTwo(data: String): String {
        val containers = parseInput(data)
        val results = getCombinations(containers, 150)
        val minSize = results.minBy { it.size }.size

        val result = results.filter { it.size == minSize }

        return result.count().toString()

    }

}