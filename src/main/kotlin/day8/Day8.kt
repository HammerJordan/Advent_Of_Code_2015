package day8

import Day

fun main() = Day8().run()
class Day8 : Day(8) {

    fun countCharacters(str: String): Int {
        return str.count()
    }

    fun countCharactersInMemory(str: String): Int {
        val trimmed = str.substring(1, str.length - 1)

        var i = 0
        val builder = StringBuilder()

        while (i < trimmed.length) {
            if (trimmed[i] == '\\') {
                if (trimmed[i + 1] == 'x') {
                    // Hex
                    val innerPtr = i + 2
                    val hex = trimmed.substring(innerPtr, innerPtr + 2)
                    builder.append(hex.toInt(16).toChar())
                    i += innerPtr + 2 - i
                    continue
                } else {
                    //Escape
                    builder.append(trimmed[i + 1])
                    i += 2
                    continue
                }
            }

            builder.append(trimmed[i])
            i++
        }
        val result = builder.toString()

        //println(result)
        return result.count()
    }

    fun countEncoded(str: String): Int {
        var i = 0
        val builder = StringBuilder()

        while (i < str.length) {
            if (i != str.length - 1)
                builder.append(str[i])
            when (str[i]) {
                '"' -> {
                    if (i == 0 || i == str.length - 1)
                        builder.append("""\"""")
                    else{
                        builder.deleteCharAt(builder.lastIndex)
                        builder.append("""\"""")
                    }
                }

                '\\' -> {
                        builder.append("""\""")
                }

                else -> {}
            }
            if (i == str.length - 1)
                builder.append(str[i])
            i++
        }
        val result = builder.toString()

        return result.count()
    }

    override fun partOne(data: String): String {
        return data
            .lines()
            .sumOf { countCharacters(it) - countCharactersInMemory(it) }
            .toString()
    }


    override fun partTwo(data: String): String {
        return data
            .lines()
            .sumOf { countEncoded(it) - countCharacters(it) }
            .toString()
    }

}