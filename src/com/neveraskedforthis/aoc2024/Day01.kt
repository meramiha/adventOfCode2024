package com.neveraskedforthis.aoc2024

import kotlin.math.abs

fun getTwoLists(input: List<String>) = input.map {
    it.split("   ").let {
        val (first, second) = it
        Pair(first.toInt(), second.toInt())
    }
}.unzip()

private fun part1(input: List<String>): Int = getTwoLists(input).map { it.sorted() }.let { (first, second) ->
    first.zip(second)
}.sumOf { abs(it.first - it.second) }

private fun part2(input: List<String>): Int = getTwoLists(input).let { (left, right) ->
    left.map { number -> number * right.count { it == number } }
}.sum()

fun main() {
    val day = "01"

    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day$day")
    part1(input).println()
    part2(input).println()
}
