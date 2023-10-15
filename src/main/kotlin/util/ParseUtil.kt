package util


fun <T> String.parsePerLine(transform: (String) -> T): List<T> {
    return this
        .lines()
        .map { transform(it) }
}

fun <T> String.parsePerLineSplitOnSpace(transform: (List<String>) -> T): List<T> {
    return this
        .lines()
        .map { transform(split(" ")) }
}