package com.neveraskedforthis.aoc2024

data class Coordinate(val y: Int, val x: Int) {
    fun <T> isIn(grid: List<List<T>>) = y in grid.indices && x in grid.first().indices
}

fun <T> List<List<T>>.at(coordinate: Coordinate): T = this[coordinate.y][coordinate.x]


fun findXmas(input: List<List<Char>>, x: Int, y: Int) = listOf(
    Coordinate(0, 1),
    Coordinate(1, 0),
    Coordinate(0, -1),
    Coordinate(-1, 0),
    Coordinate(1, 1),
    Coordinate(-1, -1),
    Coordinate(1, -1),
    Coordinate(-1, 1)
).count { dir ->
    "XMAS".withIndex().all { (idx, ch) ->
        Coordinate(y + dir.y * idx, x + dir.x * idx).let {
            it.isIn(input) && input.at(it) == ch
        }
    }
}

private fun part1(input: List<MutableList<Char>>): Int = input.indices.sumOf { y ->
    input.first().indices.sumOf { x ->
        findXmas(input, x, y)
    }
}


fun findMas(input: List<List<Char>>, x: Int, y: Int, dir: Coordinate) = "MAS".withIndex().all { (idx, ch) ->
    Coordinate(y + dir.y * idx, x + dir.x * idx).let {
        it.isIn(input) && input.at(it) == ch
    }
}

fun findMasX(input: List<List<Char>>, x: Int, y: Int): Boolean = listOf(
    Coordinate(+1, +1) to Coordinate(-1, -1),
    Coordinate(+1, -1) to Coordinate(-1, +1),
).all { (from, to) ->
    findMas(input, x + from.x, y + from.y, to) || findMas(input, x + to.x, y + to.y, from)
}

private fun part2(input: List<List<Char>>): Int = input.indices.sumOf { y ->
    input.first().indices.count { x ->
        findMasX(input, x, y)
    }
}


fun main() {
    val day = "04"

    val testInput = readInput("Day${day}_test").map { it.toCharArray().toMutableList() }

    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day$day").map { it.toCharArray().toMutableList() }
    part1(input).println()
    part2(input).println()
}
