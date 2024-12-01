import kotlin.math.abs

fun main() {

    fun getTwoLists(input: List<String>) = input.map {
        it.split("   ").let {
            val (first, second) = it
            Pair(first.toInt(), second.toInt())
        }
    }.unzip()

    fun part1(input: List<String>): Int = getTwoLists(input).map { it.sorted() }.let { (first, second) ->
        first.zip(second)
    }.sumOf { abs(it.first - it.second) }

    fun part2(input: List<String>): Int =
        getTwoLists(input).let { (left, right) ->
            left.map { number -> number * right.count { it == number } }
        }.sum()

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
