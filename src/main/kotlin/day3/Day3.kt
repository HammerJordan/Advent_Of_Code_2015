package day3

import Day

fun main() = Day3().run()
class Day3 : Day(3) {

    data class Point(val x: Int, val y: Int) {
        companion object {
            fun default(): Point {
                return Point(0, 0)
            }
        }


        operator fun plus(other: Point): Point {
            return this.copy(
                x = x + other.x,
                y = y + other.y
            )
        }

    }


    override fun partOne(data: String): String {
        var currentPosition = Point.default()
        val visited = mutableSetOf(currentPosition)

        data
            .toCharArray()
            .map { c ->
                when (c) {
                    '^' -> Point(0, 1)
                    '>' -> Point(1, 0)
                    'v' -> Point(0, -1)
                    '<' -> Point(-1, 0)
                    else -> {Point.default()}
                }
            }
            .forEach { point ->
                currentPosition += point
                visited.add(currentPosition)
            }
        return visited.count().toString()

    }


    override fun partTwo(data: String): String {
        var santaPosition = Point.default()
        var robotSantaPosition = Point.default()

        val visited = mutableSetOf(santaPosition)


        data
            .toCharArray()
            .map { c ->
                when (c) {
                    '^' -> Point(0, 1)
                    '>' -> Point(1, 0)
                    'v' -> Point(0, -1)
                    '<' -> Point(-1, 0)
                    else -> {Point.default()}
                }
            }
            .forEachIndexed{i, point->
                if (i % 2 == 0){
                    santaPosition += point
                    visited.add(santaPosition)
                }
                else{
                    robotSantaPosition += point
                    visited.add(robotSantaPosition)
                }

            }
        return visited.count().toString()
    }

}