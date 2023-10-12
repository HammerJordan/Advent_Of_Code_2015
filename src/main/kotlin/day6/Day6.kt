@file:Suppress("DuplicatedCode")

package day6

import Day

fun main() = Day6().run()
class Day6 : Day(6) {

    data class Cord(val x: Int, val y: Int) {
        companion object {
            fun parseCord(line: String): Cord {
                val split = line.split(" ").first()
                val (x, y) = split.split(',')

                return Cord(x.toInt(), y.toInt())
            }
        }
    }


    sealed class Action(val token: String) {

        abstract operator fun invoke(value: Boolean): Boolean
        abstract operator fun invoke(value: Int): Int

        data object TurnOn : Action("turn on") {
            override operator fun invoke(value: Boolean): Boolean {
                return true
            }

            override fun invoke(value: Int): Int {
                return value + 1
            }
        }

        data object TurnOff : Action("turn off") {
            override operator fun invoke(value: Boolean): Boolean {
                return false
            }

            override fun invoke(value: Int): Int {
                return 0.coerceAtLeast(value - 1)
            }
        }

        data object Toggle : Action("toggle") {
            override operator fun invoke(value: Boolean): Boolean {
                return !value
            }

            override fun invoke(value: Int): Int {
                return value + 2
            }
        }

        companion object {
            val actions = listOf(TurnOn, TurnOff, Toggle)
        }
    }

    data class Instruction(
        val minBounds: Cord,
        val maxBounds: Cord,
        val action: Action
    ) {


        companion object {
            fun parseInstruction(line: String): Instruction {
                val action = Action.actions.first { x -> line.contains(x.token) }
                var remainder = line.removePrefix(action.token).trim()
                val minBounds = Cord.parseCord(remainder)
                remainder = remainder.split(" ").last()
                val maxBounds = Cord.parseCord(remainder)


                return Instruction(
                    minBounds, maxBounds, action
                )
            }
        }
    }


    override fun partOne(data: String): String {
        val grid = Array(1000) { Array(1000) { _ -> false } }

        for (line in data.lines()) {
            val instruction = Instruction.parseInstruction(line)

            for (y in instruction.minBounds.y..instruction.maxBounds.y) {
                for (x in instruction.minBounds.x..instruction.maxBounds.x) {
                    grid[y][x] = instruction.action(grid[y][x])
                }
            }
        }

        return grid.sumOf { x -> x.count { it } }.toString()
    }


    override fun partTwo(data: String): String {
        val grid = Array(1000) { Array(1000) { _ -> 0 } }

        for (line in data.lines()) {
            val instruction = Instruction.parseInstruction(line)

            for (y in instruction.minBounds.y..instruction.maxBounds.y) {
                for (x in instruction.minBounds.x..instruction.maxBounds.x) {
                    grid[y][x] = instruction.action(grid[y][x])
                }
            }
        }

        return grid.sumOf { y -> y.sum() }.toString()
    }

}