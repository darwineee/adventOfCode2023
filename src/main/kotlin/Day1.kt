import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun day1_part1(path: String) {
    var result = 0
    Path(path).forEachLine { line ->
        val numLine = line.filter { it.isDigit() }
        val num = when (numLine.length) {
            1 -> numLine + numLine
            2 -> numLine
            else -> numLine.removeRange(1, numLine.lastIndex)
        }.toIntOrNull()
        if (num != null) result += num
    }
    println(result)
}

fun day1_part2(path: String) {
    var result = 0
    val map = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )
    val substrings = map.flatMap { listOf(it.key, it.value) }
    Path(path).forEachLine { line ->
        val numLine = findAllOverlappingMatches(line, substrings).joinToString("") { map[it] ?: it }
        val num = when (numLine.length) {
            1 -> numLine + numLine
            2 -> numLine
            else -> numLine.removeRange(1, numLine.lastIndex)
        }.toIntOrNull()
        if (num != null) result += num
    }
    println(result)
}

fun findAllOverlappingMatches(input: String, substrings: List<String>): List<String> {
    val matches = mutableListOf<String>()

    for (i in input.indices) {
        for (j in i + 1..input.length) {
            val substring = input.substring(i, j)
            if (substrings.contains(substring)) {
                matches.add(substring)
            }
        }
    }

    return matches
}