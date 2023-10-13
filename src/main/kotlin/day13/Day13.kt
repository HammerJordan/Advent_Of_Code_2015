package day13

import Day
import util.getPermutations

fun main() = Day13().run()
class Day13 : Day(13) {

    data class PeopleHappiness(
        val people: Set<String>,
        val seatedToHappiness: Map<Pair<String, String>, Int>
    )

    fun parseHappiness(data: String): PeopleHappiness {
        val people: MutableSet<String> = mutableSetOf()
        val seatedToHappiness: MutableMap<Pair<String, String>, Int> = mutableMapOf()

        for (line in data.lines()) {
            val split = line.split(" ")
            val person = split[0].trim()
            val direction = split[2].trim()
            var amount = split[3].trim().toInt()
            val nextTo = split[10].trim().removeSuffix(".")

            people.add(person)

            amount = when (direction) {
                "gain" -> amount
                "lose" -> -amount
                else -> throw IndexOutOfBoundsException()
            }

            seatedToHappiness[Pair(person, nextTo)] = amount
        }
        return PeopleHappiness(
            people = people,
            seatedToHappiness = seatedToHappiness
        )

    }

    private fun getAllPossibleRoutes(map: PeopleHappiness): List<Pair<String, Int>> {
        val possibleArrangements = map.people.getPermutations().toMutableList()
        val result = mutableListOf<Pair<String, Int>>()

        for (arrangements in possibleArrangements) {
            var totalHappiness = 0
            var dbgString = ""

            for (i in arrangements.indices) {

                if (i == 0) {
                    val left = arrangements.last()
                    val center = arrangements[0]
                    val right = arrangements[1]

                    val toLeft = map.seatedToHappiness.getOrDefault(Pair(center, left), 0)
                    val toRight = map.seatedToHappiness.getOrDefault(Pair(center, right), 0)

                    dbgString += "$left <- $toLeft [$center] $toRight -> $right\n"
                    totalHappiness += toLeft + toRight
                    continue
                }
                if (i == arrangements.lastIndex) {
                    val left = arrangements[i - 1]
                    val center = arrangements[i]
                    val right = arrangements.first()

                    val toLeft = map.seatedToHappiness.getOrDefault(Pair(center, left), 0)
                    val toRight = map.seatedToHappiness.getOrDefault(Pair(center, right), 0)

                    dbgString += "$left <- $toLeft [$center] $toRight -> $right\n"
                    totalHappiness += toLeft + toRight
                    continue
                } else {
                    val left = arrangements[i - 1]
                    val center = arrangements[i]
                    val right = arrangements[i + 1]

                    val toLeft = map.seatedToHappiness.getOrDefault(Pair(center, left), 0)
                    val toRight = map.seatedToHappiness.getOrDefault(Pair(center, right), 0)

                    dbgString += "$left <- $toLeft [$center] $toRight -> $right\n"
                    totalHappiness += toLeft + toRight
                    continue
                }

            }

            result.add(Pair(dbgString, totalHappiness))
        }

        return result

    }


    override fun partOne(data: String): String {
        val happinessMap = parseHappiness(data)
        val result = getAllPossibleRoutes(happinessMap)
        val best = result.maxBy { it.second }
        println(best.first)
        return best.second.toString()
    }


    override fun partTwo(data: String): String {
        var happinessMap = parseHappiness(data)
        happinessMap = happinessMap.copy(people = happinessMap.people.toMutableSet().apply { this.add("Self") })
        val result = getAllPossibleRoutes(happinessMap)
        val best = result.maxBy { it.second }
        println(best.first)
        return best.second.toString()
    }

}