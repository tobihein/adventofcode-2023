package day06

import readInput

class Day06 {
    fun part1(): Int {
        val readInput = readInput("day06/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day06/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int {
        val raceData = parseInput(input)
        var result = 1
        raceData.forEach {
            result *= getNumberOfWins(it)
        }
        return result
    }

    private fun getNumberOfWins(raceData: Pair<Long, Long>): Int {
        var numberOfWins = 0
        val totalTime = raceData.first
        val recordDistance = raceData.second
        for (rampupTime in 0..totalTime) {
            val travelTime = totalTime - rampupTime
            val speed = rampupTime
            val distance = travelTime * speed
            if (distance > recordDistance) {
                numberOfWins++
            }
        }
        return numberOfWins
    }

    private fun parseInput(input: List<String>): List<Pair<Long, Long>> {
        val times = parseLine(input[0])
        val distances = parseLine(input[1])
        val result = mutableListOf<Pair<Long, Long>>()
        times.forEachIndexed { index, value ->
            result.add(Pair(value, distances[index]))
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val raceData = combine(parseInput(input))

        return getNumberOfWins(raceData)
    }

    private fun combine(parseInput: List<Pair<Long, Long>>): Pair<Long, Long> {
        var joinedTime = ""
        var joinedDistance = ""
        parseInput.forEach {
            joinedTime += it.first
            joinedDistance += it.second
        }
        return Pair(joinedTime.toLong(), joinedDistance.toLong())
    }

    private fun parseLine(line: String): List<Long> =
        line.split(" ").filter { it.isNotBlank() }.filter { !it.endsWith(":") }.map { it.trim().toLong() }
}