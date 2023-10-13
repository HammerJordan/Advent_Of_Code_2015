package day11

import Day

fun main() = Day11().run()
class Day11 : Day(11) {

    fun interface IsValidPassword {
        fun invoke(pw: String): Boolean
    }

    private val hasStraight = IsValidPassword {
        var rightPtr = 0
        var seek: Int
        var last: Char
        var count: Int

        while (rightPtr < it.length) {
            last = it[rightPtr]
            seek = rightPtr + 1
            count = 1

            while (count < 3 && seek < it.length) {
                val c = it[seek]
                if (last.inc() != c)
                    break
                last = c
                count++
                seek++
            }
            if (count >= 3)
                return@IsValidPassword true

            rightPtr++
        }

        false
    }
    private val hasNoIllegalChars = IsValidPassword {
        !it.contains(Regex("[iol]+"))
    }
    private val containsTwoDoubles = IsValidPassword {
        var ptr = 0
        var count = 0

        while (ptr < it.length - 1 && count < 2) {
            val current = it[ptr]
            if (current == it[ptr + 1]) {
                count++
                ptr++
            }
            ptr++
        }

        count > 1
    }


    fun incrementPassword(pw: String): String {
        val chars = pw.toCharArray()
        var rightPtr = chars.lastIndex
        while (rightPtr >= 0) {
            var current = chars[rightPtr]
            current = current.plus(1)
            if (current > 'z')
                current = 'a'

            chars[rightPtr] = current
            rightPtr--
            if (current != 'a')
                break
        }

        return String(chars)
    }

    fun isPasswordValid(pw: String): Boolean {
        val validators = listOf(
            hasStraight,
            hasNoIllegalChars,
            containsTwoDoubles
        )

        return validators.all { it.invoke(pw) }
    }


    override fun partOne(data: String): String {
        var pw = incrementPassword(data)

        while (!isPasswordValid(pw)) {
            pw = incrementPassword(pw)
        }
        return pw
    }


    override fun partTwo(data: String): String {
        var pw = incrementPassword(data)

        while (!isPasswordValid(pw)) {
            pw = incrementPassword(pw)
        }

        pw = incrementPassword(pw)

        while (!isPasswordValid(pw)) {
            pw = incrementPassword(pw)
        }

        return pw
    }

}