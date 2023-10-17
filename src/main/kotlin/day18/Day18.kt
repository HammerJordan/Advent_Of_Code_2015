package day18

import Day

fun main() = Day18().run()
class Day18 : Day(18) {

    data class Grid(
        val grid: MutableList<MutableList<Boolean>>
    ) {
        fun printGrid() {
            for (line in grid) {
                println(line.map { if (it) '#' else '.' }.joinToString(separator = "", prefix = "[", postfix = "]"))
            }
        }

        fun getNeighbors(position: Pair<Int, Int>): Map<Pair<Int, Int>, Boolean> {
            val result = mutableMapOf<Pair<Int, Int>, Boolean>()
            val (startX, startY) = position

            for (y in startY - 1..startY + 1) {
                if (y < 0 || y >= grid.size)
                    continue

                for (x in startX - 1..startX + 1) {
                    if (x < 0 || x >= grid.size)
                        continue
                    if (x to y == position)
                        continue
                    result[x to y] = grid[y][x]
                }
            }

            return result
        }

        fun toggleCornersOn() {
            val maxX = grid[0].size - 1
            val maxY = grid.size - 1
            grid[0][0] = true
            grid[0][maxX] = true
            grid[maxY][0] = true
            grid[maxY][maxX] = true
        }

    }

    fun parseInput(input: String): Grid {

        val gird = input
            .lines()
            .map { line ->
                line
                    .toCharArray()
                    .map {
                        it == '#'
                    }.toMutableList()
            }.toMutableList()

        return Grid(gird)
    }

    fun toggleLights(gridContainer: Grid): Grid {
        val newGrid: MutableList<MutableList<Boolean>> = mutableListOf()
        val grid = gridContainer.grid

        for (y in grid.indices) {
            val currentLine = mutableListOf<Boolean>()
            for (x in grid[y].indices) {
                var current = grid[y][x]
                val neighbors = gridContainer.getNeighbors(x to y)
                val neighborOnCount = neighbors.count { it.value }
                if (current) {
                    if (neighborOnCount !in 2..3) {
                        current = false
                    }
                } else {
                    if (neighborOnCount == 3)
                        current = true
                }
                currentLine.add(current)
            }
            newGrid.add(currentLine)
        }


        return Grid(newGrid)
    }


    override fun partOne(data: String): String {
        var grid = parseInput(data)
        for (i in 0..<100) {
            grid = toggleLights(grid)
        }

        return grid.grid.sumOf { row -> row.count { it } }.toString()
    }


    override fun partTwo(data: String): String {
        var grid = parseInput(data)
        grid.toggleCornersOn()
        for (i in 0..<100) {
            grid = toggleLights(grid)
            grid.toggleCornersOn()
        }

        return grid.grid.sumOf { row -> row.count { it } }.toString()
    }

}