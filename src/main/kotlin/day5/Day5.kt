package day5

import Day

fun main() = Day5().run()
class Day5 : Day(5) {

    fun interface Rule {
        fun isValid(data: String): Boolean
    }

    override fun partOne(data: String): String {
        val rules = listOf(
            Rule { str ->
                val vowels = "aeiou".toList()
                str
                    .map { x -> vowels.contains(x) }
                    .count { x -> x } >= 3
            },
            Rule { str ->
                str
                    .windowed(size = 2, step = 1)
                    .any { x ->
                        x.first() == x.last()
                    }
            },
            Rule { str ->
                val naughtyWords = listOf(
                    "ab",
                    "cd",
                    "pq",
                    "xy"
                )

                !naughtyWords.any { naughtyWord ->
                    str.contains(naughtyWord)
                }
            }
        )

        return data
            .lines()
            .count { line -> rules.all { rule -> rule.isValid(line) } }
            .toString()
    }


    override fun partTwo(data: String): String {
        val rules = listOf(
            Rule { str ->
                val pairs = str
                    .windowed(2, 1)

                pairs
                    .forEachIndexed { index, pair ->
                        if (pairs.drop(index + 2).contains(pair))
                            return@Rule true
                    }

                false
            },
            Rule { str ->
                str
                    .windowed(3, 1)
                    .any { window -> window.first() == window.last() }
            },
        )

        rules.forEachIndexed { i, rule ->
            val result = rule.isValid(data)
        }

        return data
            .lines()
            .count { line -> rules.all { rule -> rule.isValid(line) } }
            .toString()
    }

}