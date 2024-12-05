package com.neveraskedforthis.aoc2024


enum class State {
    EMPTY, M, U, L, OPEN_BRACKET, FIRST_NUMBER, COMMA, SECOND_NUMBER
}

private fun part1(input: String): Int {
    var state = State.EMPTY
    var sum = 0

    var firstNumber = 0
    var secondNumber = 0
    input.forEach {
        when (Pair(it, state)) {
            Pair('m', State.EMPTY) -> {
                state = State.M
            }

            Pair('u', State.M) -> {
                state = State.U
            }

            Pair('l', State.U) -> {
                state = State.L
            }

            Pair('(', State.L) -> {
                state = State.OPEN_BRACKET
            }

            Pair(',', State.FIRST_NUMBER) -> {
                state = State.COMMA
            }

            Pair(')', State.SECOND_NUMBER) -> {
                sum += firstNumber * secondNumber

                state = State.EMPTY
                firstNumber = 0
                secondNumber = 0
            }


            else -> {
                if (it.isDigit()) {
                    if (state == State.OPEN_BRACKET || state == State.FIRST_NUMBER) {
                        firstNumber = (firstNumber * 10) + (it - '0')
                        state = State.FIRST_NUMBER
                        return@forEach
                    }
                    if (state == State.COMMA || state == State.SECOND_NUMBER) {
                        secondNumber = (secondNumber * 10) + (it - '0')
                        state = State.SECOND_NUMBER
                        return@forEach
                    }
                }

                state = State.EMPTY
                firstNumber = 0
                secondNumber = 0
            }
        }
    }

    return sum
}

private fun part2(input: String): Int {
    var state = State.EMPTY
    var sum = 0

    var firstNumber = 0
    var secondNumber = 0
    var isActive = true

    input.forEachIndexed { idx, char ->
        when (Pair(char, state)) {
            Pair('m', State.EMPTY) -> {
                state = State.M
            }

            Pair('u', State.M) -> {
                state = State.U
            }

            Pair('l', State.U) -> {
                state = State.L
            }

            Pair('(', State.L) -> {
                state = State.OPEN_BRACKET
            }

            Pair(',', State.FIRST_NUMBER) -> {
                state = State.COMMA
            }

            Pair(')', State.SECOND_NUMBER) -> {
                if (isActive) {
                    sum += firstNumber * secondNumber
                }

                state = State.EMPTY
                firstNumber = 0
                secondNumber = 0
            }

            else -> {
                if (char.isDigit()) {
                    if (state == State.OPEN_BRACKET || state == State.FIRST_NUMBER) {
                        firstNumber = (firstNumber * 10) + (char - '0')
                        state = State.FIRST_NUMBER
                        return@forEachIndexed
                    }
                    if (state == State.COMMA || state == State.SECOND_NUMBER) {
                        secondNumber = (secondNumber * 10) + (char - '0')
                        state = State.SECOND_NUMBER
                        return@forEachIndexed
                    }
                }

                if (char == 'd') {
                    when {
                        input.substring(idx, idx + "do()".length) == "do()" -> {
                            isActive = true
                        }

                        input.substring(idx, idx + "don't()".length) == "don't()" -> {
                            isActive = false
                        }
                    }
                }


                state = State.EMPTY
                firstNumber = 0
                secondNumber = 0
            }
        }
    }

    return sum
}

fun main() {
    val day = "03"

    val testInput = readInput("Day${day}_test").joinToString("\n")

    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    val input = readInput("Day$day").joinToString("\n")

    part1(input).println()
    part2(input).println()
}
