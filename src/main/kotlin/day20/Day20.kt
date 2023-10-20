package day20

import Day

fun main() = Day20().run()
class Day20 : Day(20) {


    override fun partOne(data: String): String {
        val expected = 34000000
        val houses = MutableList(expected / 10) { 0 }

        for (elfId in 1..<expected / 10) {
            for (house in elfId..expected / 10 step (elfId)) {
                houses[house - 1] += elfId * 10
            }
        }
        return (houses.indexOfFirst { it >= expected } + 1).toString()
    }


    override fun partTwo(data: String): String {
        val expected = 34000000
        val houses = MutableList(expected / 10) { 0 }

        for (elfId in 1..<expected / 10) {

            for (house in (elfId..expected / 10 step (elfId)).take(50)) {
                houses[house - 1] += elfId * 11


            }
        }
        return (houses.indexOfFirst { it >= expected } + 1).toString()
    }

}