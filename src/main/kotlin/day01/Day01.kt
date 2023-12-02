package day01

import readInput

class Day01 {
    fun part1(): Int {
        val readInput = readInput("day01/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day01/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            extractDigits(it)
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { extractDigits2(it) }
    }

    private fun extractDigits(input: String): Int {
        val digits = input.toCharArray().filter { it.isDigit() }
        val first = digits.first()
        val last = digits.last()
        return 10 * first.digitToInt() + last.digitToInt()
    }

    private fun extractDigits2(input: String): Int {
        val searchValues = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        )
        val firstIndices = searchValues.map { Pair(input.indexOf(it), it) }.filter { it.first >= 0 }
        val lastIndices = searchValues.map { Pair(input.lastIndexOf(it), it) }.filter { it.first >= 0 }
        var allIndices = firstIndices + lastIndices
        allIndices = allIndices.sortedBy { it.first }
        val first = allIndices.first().second
        val last = allIndices.last().second

        return 10 * toInt(first) + toInt(last)
    }

    private fun toInt(input: String): Int = replaceTextNumbers(input).toInt()

    private fun replaceTextNumbers(input: String): String =
        input.replace("one", "1")
            .replace("two", "2")
            .replace("three", "3")
            .replace("four", "4")
            .replace("five", "5")
            .replace("six", "6")
            .replace("seven", "7")
            .replace("eight", "8")
            .replace("nine", "9")
}