package day02

import readInput

class Day02 {
    fun part1(): Int {
        val readInput = readInput("day02/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day02/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int = input.sumOf {
        getValueForLine(it)
    }

    fun part2(input: List<String>): Int = input.sumOf {
        getValueForLine2(it)
    }

    private fun getValueForLine2(line: String): Int {
        val maximums = mutableMapOf<String, Int>()
        line.split(";", ",", ":").forEach {
            val split = it.trim().split(" ")
            if (split[0] != "Game") {
                maximums.putIfAbsent(split[1], split[0].toInt())
                val currentMaximum = maximums[split[1]]
                if (currentMaximum != null) {
                    if (currentMaximum < split[0].toInt()) {
                        maximums[split[1]] = split[0].toInt()
                    }
                }
            }
        }
        return maximums.values.reduce { product, element -> product * element }
    }

    private fun getValueForLine(line: String): Int {
        var game = 0
        line.split(":", ",", ";").forEachIndexed { index, token ->
            val pair = token.trim().split(" ")
            if (index == 0) {
                game = pair[1].toInt()
            } else {
                if (!isValid(token)) {
                    game = 0
                }
            }
        }
        return game
    }

    private fun split(line: String): List<String> =
        line.split(";,:").toList()

    private fun isValid(token: String): Boolean {
        val split = token.trim().split(" ")
        val limits = mapOf(Pair("red", 12), Pair("green", 13), Pair("blue", 14))
        if (limits.containsKey(split[1])) {
            return limits[split[1]]!! >= split[0].toInt()
        }
        return true
    }
}