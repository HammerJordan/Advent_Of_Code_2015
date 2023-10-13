package day9

import Day
import util.getPermutations

fun main() = Day9().run()
class Day9 : Day(9) {

    data class CityRoutes(
        val cities: Set<String>,
        val distances: Map<Pair<String, String>, Int>
    )

    fun parseRoutes(data: String): CityRoutes {
        val cities: MutableSet<String> = mutableSetOf()
        val distances: MutableMap<Pair<String, String>, Int> = mutableMapOf()

        for (line in data.lines()) {
            val split = line.split(" ")
            val city1 = split[0].trim()
            val city2 = split[2].trim()
            val distance = split[4].trim().toInt()

            cities.add(city1)
            cities.add(city2)

            distances[Pair(city1, city2)] = distance
        }

        return CityRoutes(
            cities = cities,
            distances = distances
        )


    }

    fun getAllPossibleRoutes(routes: CityRoutes): List<Pair<String, Int>> {
        val possiblePaths = routes.cities.getPermutations()


        val result = mutableListOf<Pair<String, Int>>()
        for (possiblePath in possiblePaths) {
            var distance = 0

            for ((first, second) in possiblePath.windowed(2)) {
                distance += if (routes.distances.containsKey(Pair(first,second))){
                    routes.distances.getValue(Pair(first,second))
                } else if(routes.distances.containsKey(Pair(second,first))) {
                    routes.distances.getValue(Pair(second,first))
                } else{
                    throw Error()
                }
            }

            result.add(Pair(possiblePath.joinToString(" -> "),distance))
        }

        return result
    }




    override fun partOne(data: String): String {
        val routes = parseRoutes(data)
        val possibleRoutes = getAllPossibleRoutes(routes)

        return possibleRoutes.minOf { it.second }.toString()
    }


    override fun partTwo(data: String): String {
        val routes = parseRoutes(data)
        val possibleRoutes = getAllPossibleRoutes(routes)

        return possibleRoutes.maxOf { it.second }.toString()
    }

}