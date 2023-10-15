package day16

import Day

fun main() = Day16().run()
class Day16 : Day(16) {
    companion object {
        val regex = """Sue (\d+): (\w+): (\d+), (\w+): (\d+), (\w+): (\d+)""".toRegex()

        val sueToFind = mapOf(
            "children" to 3,
            "cats" to 7,
            "samoyeds" to 2,
            "pomeranians" to 3,
            "akitas" to 0,
            "vizslas" to 0,
            "goldfish" to 5,
            "trees" to 3,
            "cars" to 2,
            "perfumes" to 1,
        )

    }

    data class Sue(
        val id: Int,
        val properties: Map<String, Int>
    )

    private fun parseSues(data: String): List<Sue> {

        return data
            .lines()
            .map {
                val values = regex.find(it)?.groupValues!!

                val name = values[1]

                val properties = values
                    .drop(2)
                    .chunked(2).associate { (first, second) ->
                        first.removeSuffix(":") to second.toInt()
                    }

                Sue(name.toInt(), properties)
            }
    }


    override fun partOne(data: String): String {
        val sues = parseSues(data)

        for (su in sues) {
            val prop = su.properties
            var found = true

            for (p in prop) {
                if (sueToFind.containsKey(p.key)) {
                    if (sueToFind[p.key] != p.value) {
                        found = false
                        break
                    }
                }
            }

            if (found) {
                return su.id.toString()
            }

        }

        return "Not Found"
    }


    override fun partTwo(data: String): String {
        val sues = parseSues(data)

        for (su in sues) {
            val prop = su.properties
            var found = true

            for (p in prop) {
                if (sueToFind.containsKey(p.key)) {
                    when (p.key) {
                        "cats", "trees" -> {
                            if(p.value <= sueToFind[p.key]!!){
                                found = false
                                break
                            }
                        }
                        "pomeranians", "goldfish" -> {
                            if(p.value >= sueToFind[p.key]!!){
                                found = false
                                break
                            }
                        }
                        else -> {
                            if (sueToFind[p.key] != p.value) {
                                found = false
                                break
                            }
                        }

                    }

                }
            }

            if (found) {
                return su.id.toString()
            }

        }

        return "-1"
    }

}