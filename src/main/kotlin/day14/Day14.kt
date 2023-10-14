package day14

import Day

fun main() = Day14().run()
class Day14 : Day(14) {

    data class Reindeer(val name: String, val speed: Int, val duration: Int, val restDuration: Int)

    fun parseReindeer(data: String): List<Reindeer> {
        val result = mutableListOf<Reindeer>()

        for (line in data.lines()) {
            val split = line.split(" ")
            val name = split[0]
            val speed = split[3].toInt()
            val duration = split[6].toInt()
            val restDuration = split[13].toInt()

            result.add(
                Reindeer(
                    name = name,
                    speed = speed,
                    duration = duration,
                    restDuration = restDuration
                )
            )

        }
        return result
    }

    fun calculateDistances(reindeer: List<Reindeer>, duration: Int): Map<String, Int> {
        val result = reindeer.associate { it.name to 0 }.toMutableMap()
        for (i in 0..<duration) {
            for (r in reindeer) {
                val currentRunDuration = i % (r.duration + r.restDuration)
                if (currentRunDuration < r.duration)
                    result[r.name] = result[r.name]!! + r.speed
            }
        }
        return result
    }

    fun calculateScore(reindeer: List<Reindeer>, duration: Int): Map<String, Int> {
        val distances = reindeer.associate { it.name to 0 }.toMutableMap()
        val score = reindeer.associate { it.name to 0 }.toMutableMap()

        for (i in 0..<duration) {
            for (r in reindeer) {
                val currentRunDuration = i % (r.duration + r.restDuration)
                if (currentRunDuration < r.duration)
                    distances[r.name] = distances[r.name]!! + r.speed
            }

            var bestScore = Int.MIN_VALUE
            val topReindeer = mutableListOf<String>()
            for (d in distances) {
                if (d.value < bestScore)
                    continue

                if (d.value == bestScore) {
                    topReindeer.add(d.key)
                    continue
                }

                bestScore = d.value
                topReindeer.clear()
                topReindeer.add(d.key)
            }

            for (top in topReindeer) {
                score[top] = score[top]!! + 1
            }

        }
        return score
    }


    override fun partOne(data: String): String {
        val reindeer = parseReindeer(data)
        val distances = calculateDistances(reindeer, 2503)

        val best = distances.maxOf { it.value }
        return best.toString()
    }


    override fun partTwo(data: String): String {
        val reindeer = parseReindeer(data)
        val distances = calculateScore(reindeer, 2503)

        val best = distances.maxOf { it.value }
        return best.toString()
    }

}