package day04

import readInput

class Day04 {
    fun part1(): Int {
        val readInput = readInput("day04/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day04/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int =
        input.sumOf {
            worthPoints(it)
        }

    fun part2(input: List<String>): Int {
        val scratchCardCount = mutableMapOf<Int, Int>()
        input.forEachIndexed { index, _ ->
            scratchCardCount[index] = 1
        }
        input.forEachIndexed { index, line ->
            val winningNumbers = countWInningNumbers(line)
            val currentScratchCardCount = scratchCardCount[index]
            for (i in 1..winningNumbers) {
                scratchCardCount[index + i]
                scratchCardCount[index + i] = scratchCardCount[index + i]!! + currentScratchCardCount!!
            }
        }
        return scratchCardCount.values.sum()
    }

    private fun countWInningNumbers(line: String): Int {
        val tokens = line.split(":", "|")
        val winningNumbers =
            tokens[1].trim().split(" ").map { it.trim() }.filter { it.isNotBlank() }.map { it.toInt() }.toSet()
        val yourNumbers =
            tokens[2].trim().split(" ").map { it.trim() }.filter { it.isNotBlank() }.map { it.toInt() }.toSet()
        return winningNumbers.intersect(yourNumbers).size
    }

    private fun worthPoints(line: String): Int {
        val intersection = countWInningNumbers(line)
        if (intersection == 0) {
            return 0
        }
        return Math.pow(2.0, (intersection - 1).toDouble()).toInt()
    }

}