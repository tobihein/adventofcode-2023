package day10

import readInput

class Day10 {
    private val right = Pair(1, 0)
    private val left = Pair(-1, 0)
    private val down = Pair(0, 1) // mirrored on x-axes
    private val up = Pair(0, -1) // mirrored on x-axes
    fun part1(): Int {
        val readInput = readInput("day10/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day10/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int {
        val width = input[0].length
        val startCoordinates = getStartCoordinates(input)
        println("Start: $startCoordinates")
        val startingDirection = right
        var max = 0
        for (start in listOf(up, down, left, right)) {
            val currentMax = getMaxDistanceForCicla(startCoordinates, startingDirection, width, input)
            max = if (currentMax > max) currentMax else max
        }
        return max
    }

    private fun getMaxDistanceForCicla(
        startCoordinates: Pair<Int, Int>,
        startingDirection: Pair<Int, Int>,
        width: Int,
        input: List<String>
    ): Int {
        var goToPosition = add(startCoordinates, startingDirection)
        var currentDirection = startingDirection
        var counter = 1
        var canContinue = true
        while (canContinue && goToPosition.first >= 0 && goToPosition.second < width && goToPosition != startCoordinates) {
            val nextElement = input[goToPosition.second].toCharArray()[goToPosition.first]
            if (allowsFrom(
                    nextElement,
                    currentDirection
                )
            ) {
                currentDirection = nextDirection(nextElement, currentDirection)
                goToPosition = add(goToPosition, currentDirection)
                counter++
            } else {
                canContinue = false
            }
        }
        return counter / 2
    }

    fun part2(input: List<String>): Int = 0

    private fun getStartCoordinates(input: List<String>): Pair<Int, Int> {
        val column =
            input.mapIndexed { idx, line -> Pair(idx, line) }.filter { it.second.contains("S") }.map { it.first }
                .first()
        val row = input[column].indexOf("S")
        return Pair(row, column)
    }

    private fun add(a: Pair<Int, Int>, b: Pair<Int, Int>): Pair<Int, Int> =
        Pair(a.first + b.first, a.second + b.second)

    private fun allowsFrom(c: Char, from: Pair<Int, Int>): Boolean {
        return when (c) {
            '|' -> return (from == up || from == down)
            '-' -> return (from == left || from == right)
            'L' -> return (from == down || from == left)
            'J' -> return (from == down || from == right)
            '7' -> return (from == right || from == up)
            'F' -> return (from == up || from == left)
            'S' -> return true
            else -> false
        }
    }

    private fun nextDirection(c: Char, from: Pair<Int, Int>): Pair<Int, Int> {
        return when (c) {
            '|' -> return (if (from == up) up else down)
            '-' -> return (if (from == right) right else left)
            'L' -> return (if (from == down) right else up)
            'J' -> return (if (from == down) left else up)
            '7' -> return (if (from == right) down else left)
            'F' -> return (if (from == up) right else down)
            else -> Pair(0, 0)
        }
    }
}