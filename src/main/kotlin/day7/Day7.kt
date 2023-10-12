package day7

import Day

fun main() = Day7().run()
class Day7 : Day(7) {

    fun interface ParseComponent {
        fun parseComponent(line: String): Result<Circuit>
    }

    sealed interface Circuit {
        fun execute(circuit: MutableMap<String, UShort>);
        fun canExecute(circuit: MutableMap<String, UShort>): Boolean
        data class DataIn(val data: UShort, val line: String) : Circuit {
            override fun execute(circuit: MutableMap<String, UShort>) {
                circuit[line] = data
            }

            override fun canExecute(circuit: MutableMap<String, UShort>): Boolean {
                return true
            }

            companion object {
                val parser = ParseComponent {
                    if (it.split(" ").count() > 3)
                        return@ParseComponent Result.failure(Throwable())

                    val split = it.split("->")
                    split[0].trim().toUShortOrNull()?.let { data ->
                        return@ParseComponent Result.success(DataIn(data, split[1].trim()))
                    }

                    return@ParseComponent Result.failure(Throwable())
                }
            }
        }

        data class LineIn(val lineIn: String, val lineOut: String) : Circuit {
            override fun execute(circuit: MutableMap<String, UShort>) {
                circuit[lineOut] = circuit[lineIn]!!
            }

            override fun canExecute(circuit: MutableMap<String, UShort>): Boolean {
                return circuit.containsKey(lineIn)
            }

            companion object {
                val parser = ParseComponent {
                    if (it.split(" ").count() > 3)
                        return@ParseComponent Result.failure(Throwable())

                    val split = it.split("->")
                    split[0].trim().toUShortOrNull()?.let { _ ->
                        return@ParseComponent Result.failure(Throwable())
                    }
                    return@ParseComponent Result.success(LineIn(split[0].trim(), split[1].trim()))

                }
            }
        }

        data class And(val left: String, val right: String, val line: String) : Circuit {
            override fun execute(circuit: MutableMap<String, UShort>) {
                val left = if (leftIsConst()) {
                    left.toUShort()
                } else {
                    circuit.getOrDefault(left, 0U)
                }


                val right = circuit.getOrDefault(right, 0U)
                circuit[line] = left.and(right)
            }

            override fun canExecute(circuit: MutableMap<String, UShort>): Boolean {
                return (circuit.containsKey(left) || leftIsConst()) && circuit.containsKey(right)
            }

            private fun leftIsConst(): Boolean {
                return this.left.toIntOrNull() != null
            }

            companion object {
                val parser = ParseComponent {
                    if (!it.contains("AND"))
                        return@ParseComponent Result.failure(Throwable())

                    val tokens = it.split(" ")

                    val left = tokens[0]
                    val right = tokens[2]
                    val output = tokens[4].trim()

                    Result.success(And(left, right, output))
                }
            }
        }

        data class Or(val left: String, val right: String, val line: String) : Circuit {
            override fun execute(circuit: MutableMap<String, UShort>) {
                val left = circuit.getOrDefault(left, 0U)
                val right = circuit.getOrDefault(right, 0U)
                circuit[line] = left or right
            }

            override fun canExecute(circuit: MutableMap<String, UShort>): Boolean {
                return circuit.containsKey(left) && circuit.containsKey(right)
            }

            companion object {
                val parser = ParseComponent {
                    if (!it.contains("OR"))
                        return@ParseComponent Result.failure(Throwable())

                    val tokens = it.split(" ")

                    val left = tokens[0]
                    val right = tokens[2]
                    val output = tokens[4].trim()

                    Result.success(Or(left, right, output))
                }
            }
        }

        data class LeftShift(val left: String, val by: Int, val line: String) : Circuit {
            override fun execute(circuit: MutableMap<String, UShort>) {
                val item = circuit.getOrDefault(left, 0U).toInt()
                val result = item shl by
                circuit[line] = result.toUShort()
            }

            override fun canExecute(circuit: MutableMap<String, UShort>): Boolean {
                return circuit.containsKey(left)
            }

            companion object {
                val parser = ParseComponent {
                    if (!it.contains("LSHIFT"))
                        return@ParseComponent Result.failure(Throwable())

                    val tokens = it.split(" ")

                    val left = tokens[0]
                    val by = tokens[2].toInt()
                    val output = tokens[4].trim()

                    Result.success(LeftShift(left, by, output))
                }
            }
        }

        data class RightShift(val left: String, val by: Int, val line: String) : Circuit {
            override fun execute(circuit: MutableMap<String, UShort>) {
                val item = circuit.getOrDefault(left, 0U).toInt()
                val result = item shr by
                circuit[line] = result.toUShort()
            }

            override fun canExecute(circuit: MutableMap<String, UShort>): Boolean {
                return circuit.containsKey(left)
            }

            companion object {
                val parser = ParseComponent {
                    if (!it.contains("RSHIFT"))
                        return@ParseComponent Result.failure(Throwable())

                    val tokens = it.split(" ")

                    val left = tokens[0]
                    val by = tokens[2].toInt()
                    val output = tokens[4].trim()

                    Result.success(RightShift(left, by, output))
                }
            }
        }

        data class Not(val data: String, val line: String) : Circuit {
            override fun execute(circuit: MutableMap<String, UShort>) {
                val item = circuit.getOrDefault(data, 0U)
                circuit[line] = item.inv()
            }

            override fun canExecute(circuit: MutableMap<String, UShort>): Boolean {
                return circuit.containsKey(data)
            }

            companion object {
                val parser = ParseComponent {
                    if (!it.contains("NOT"))
                        return@ParseComponent Result.failure(Throwable())

                    val tokens = it.split(" ")
                    val data = tokens[1]
                    val output = tokens[3]

                    Result.success(Not(data, output))
                }
            }
        }

        companion object {
            private val parsers = listOf(

                And.parser,
                Or.parser,
                LeftShift.parser,
                RightShift.parser,
                Not.parser,
                LineIn.parser,
                DataIn.parser,
            )

            fun parseCircuitItem(line: String): Circuit {
                for (parser in parsers) {
                    val result = parser.parseComponent(line)
                    if (result.isSuccess) {
                        return result.getOrThrow()
                    }
                }
                throw NotImplementedError()
            }
        }
    }


    override fun partOne(data: String): String {
        val wires = mutableMapOf<String, UShort>()

        val instructions = data.lines().map { Circuit.parseCircuitItem(it) }.toMutableList()

        while (instructions.isNotEmpty()) {
            val canExecute = instructions.filter { it.canExecute(wires) }
            instructions.removeAll(canExecute)

            for (instruction in canExecute) {
                instruction.execute(wires)
            }
        }

        val builder = StringBuffer()

        for (wire in wires.toSortedMap()) {
            builder.append("${wire.key}: ${wire.value}")
            builder.appendLine()
        }
        return wires.toSortedMap().map { "${it.key}: ${it.value}" }.joinToString(separator = "\n")
    }


    override fun partTwo(data: String): String {
        val wires = mutableMapOf<String, UShort>()

        val newData = data.replace("14146 -> b", "956 -> b")

        val instructions = newData.lines().map { Circuit.parseCircuitItem(it) }.toMutableList()

        while (instructions.isNotEmpty()) {
            val canExecute = instructions.filter { it.canExecute(wires) }
            instructions.removeAll(canExecute)

            for (instruction in canExecute) {
                instruction.execute(wires)
            }
        }

        val builder = StringBuffer()

        for (wire in wires.toSortedMap()) {
            builder.append("${wire.key}: ${wire.value}")
            builder.appendLine()
        }
        return wires.toSortedMap().map { "${it.key}: ${it.value}" }.joinToString(separator = "\n")
    }

}