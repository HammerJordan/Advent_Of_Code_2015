package util

import java.util.*

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