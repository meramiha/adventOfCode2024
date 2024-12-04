package com.neveraskedforthis.aoc2024

fun readReports(input: List<String>) = input.map {
    it.split(" ").map { it.toInt() }
}

fun List<Int>.isSafe() = dropLast(1).indices.map { i ->
    this[i] - this[i + 1]
}.let { diffs -> diffs.all { it >= 1 && it <= 3 } || diffs.all { it >= -3 && it <= -1 } }

private fun part1(input: List<String>) = readReports(input).map { report ->
    report.isSafe()
}.count { it }

private fun part2(input: List<String>) = readReports(input).map { levels ->
    levels.indices.map {
        levels.subList(0, it) + levels.subList(it + 1, levels.size)
    }
}.map { reportsWithoutElement ->
    reportsWithoutElement.map { report ->
        report.isSafe()
    }.any { it }
}.count { it }


fun main() {
    val day = "02"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day$day")
    part1(input).println()
    part2(input).println()
}
