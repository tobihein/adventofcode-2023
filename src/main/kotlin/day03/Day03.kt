package day03

import readInput

class Day03 {
    fun part1(): Int {
        val readInput = readInput("day03/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day03/input")
        return part2(readInput)
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val symbols = mutableListOf<Pair<Int, Int>>()
        input.forEachIndexed { row, line ->
            symbols.addAll(readSymbols(line, row))
        }
        val numbers = mutableListOf<Triple<Pair<Int, Int>, Pair<Int, Int>, Int>>()
        input.forEachIndexed { row, line ->
            numbers.addAll(getAdjancedNumbersTriples(line, row, symbols))
        }
        return symbols.sumOf {
            getGear(it, numbers)
        }
    }

    private fun getGear(symbol: Pair<Int, Int>, numbers: List<Triple<Pair<Int, Int>, Pair<Int, Int>, Int>>): Int {
        val adjancedNumbers = mutableListOf<Int>()
        numbers.forEach {
            if (isAdjanced(it.first, it.second, listOf(symbol))) {
                adjancedNumbers.add(it.third)
            }
        }
        if (adjancedNumbers.size == 2) {
            return adjancedNumbers[0] * adjancedNumbers[1]
        }
        return 0
    }

    private fun getAdjacentNumbers(line: String, row: Int, symbols: List<Pair<Int, Int>>): List<Int> {
        val numbers = mutableListOf<Int>()
        var lastChar = ' '
        var number = ""
        var bottomLeft = Pair(-1, -1)
        var upperRight = Pair(-1, -1)
        line.forEachIndexed { column, char ->
            if (char.isDigit()) {
                if (!lastChar.isDigit()) {
                    bottomLeft = Pair(column - 1, row - 1)
                }
                number += char
            }
            if ((column == (line.length - 1) || endOfNumber(char, lastChar)) && number != "") {
                val currentNumber = number.toInt()
                upperRight = Pair(column, row + 1)
                if (isAdjanced(bottomLeft, upperRight, symbols)) {
                    numbers.add(currentNumber)
                }
                number = ""
            }
            lastChar = char
        }
        return numbers
    }

    private fun getAdjancedNumbersTriples(
        line: String,
        row: Int,
        symbols: List<Pair<Int, Int>>
    ): List<Triple<Pair<Int, Int>, Pair<Int, Int>, Int>> {
        val numbers = mutableListOf<Triple<Pair<Int, Int>, Pair<Int, Int>, Int>>()
        var lastChar = ' '
        var number = ""
        var bottomLeft = Pair(-1, -1)
        var upperRight = Pair(-1, -1)

        line.forEachIndexed { column, char ->
            if (char.isDigit()) {
                if (!lastChar.isDigit()) {
                    bottomLeft = Pair(column - 1, row - 1)
                }
                number += char
            }
            if ((column == (line.length - 1) || endOfNumber(char, lastChar)) && number != "") {
                val currentNumber = number.toInt()
                upperRight = Pair(column, row + 1)
                if (isAdjanced(bottomLeft, upperRight, symbols)) {
                    numbers.add(Triple(bottomLeft, upperRight, currentNumber))
                }
                number = ""
            }
            lastChar = char
        }
        return numbers
    }

    private fun isAdjanced(
        bottomLeft: Pair<Int, Int>,
        upperRight: Pair<Int, Int>,
        symbols: List<Pair<Int, Int>>
    ): Boolean {
        symbols.forEach {
            if (bottomLeft.first <= it.first && it.first <= upperRight.first) {
                if (bottomLeft.second <= it.second && it.second <= upperRight.second) {
                    return true
                }
            }
        }
        return false
    }

    private fun endOfNumber(current: Char, previous: Char): Boolean = !current.isDigit() && previous.isDigit()

    fun part1(input: List<String>): Int {
        var result = 0
        val symbols = mutableListOf<Pair<Int, Int>>()
        input.forEachIndexed { row, line ->
            symbols.addAll(readSymbols(line, row))
        }
        val adjacanedNumbers = mutableListOf<Int>()
        input.forEachIndexed { row, line ->
            val numbers = getAdjacentNumbers(line, row, symbols)
            result += numbers.sum()
        }
        return result
    }

    private fun readSymbols(input: String, row: Int): List<Pair<Int, Int>> {
        val symbols = mutableListOf<Pair<Int, Int>>()
        input.toCharArray().forEachIndexed { column, char ->
            if (!char.isDigit() && char != '.') {
                symbols.add(Pair(column, row))
            }
        }
        return symbols
    }
}