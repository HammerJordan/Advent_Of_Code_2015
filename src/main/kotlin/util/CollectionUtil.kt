package util

import java.util.*


fun <T : Any> List<T>.combinations(r: Int, replace: Boolean = false): Sequence<List<T>> {
    val n = count()
    if (r > n) return sequenceOf()
    return sequence {
        var indices = if (replace) 0.repeat(r).toMutableList() else (0..<r).toMutableList()
        while (true) {
            yield(indices.map { this@combinations[it] })
            var i = r - 1
            loop@ while (i >= 0) {
                when (replace) {
                    true -> if (indices[i] != n - 1) break@loop
                    false -> if (indices[i] != i + n - r) break@loop
                }
                i--
            }
            if (i < 0) break
            when (replace) {
                true -> indices = (indices.take(i) + (indices[i] + 1).repeat(r - i)).toMutableList()
                false -> {
                    indices[i] += 1
                    (i + 1..<r).forEach { indices[it] = indices[it - 1] + 1 }
                }
            }
        }
    }
}

fun <T : Any> T.repeat(times: Int? = null): Sequence<T> = sequence {
    var count = 0
    while (times == null || count++ < times) yield(this@repeat)
}

fun <T : Any> Set<T>.getPermutations(): List<List<T>> {
    val permutations = mutableListOf<List<T>>()
    permutationsRecursive(this.toList(), 0, permutations)
    return permutations
}

private fun <T> permutationsRecursive(input: List<T>, index: Int, answers: MutableList<List<T>>) {
    if (index == input.lastIndex) answers.add(input.toList())
    for (i in index..input.lastIndex) {
        Collections.swap(input, index, i)
        permutationsRecursive(input, index + 1, answers)
        Collections.swap(input, i, index)
    }
}