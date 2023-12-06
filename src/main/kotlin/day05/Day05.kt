package day05

import readInput

class Day05 {
    fun part1(): Long {
        val readInput = readInput("day05/input")
        return part1(readInput)
    }

    fun part2(): Long {
        val readInput = readInput("day05/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Long {
        var values = input[0].split(" ").filter { !it.contains("seed") }.map { it.trim().toLong() }
        val mappingRules = mutableListOf<Triple<Long, Long, Long>>()
        for (i in 3 until input.size) {
            val line = input[i]
            if (line.isBlank() || i == (input.size - 1)) {
                values = doMappings(mappingRules, values)
                mappingRules.clear()
            } else if (!line.endsWith("map:")) {
                val map = line.split(" ").map { it.toLong() }
                mappingRules.add(Triple(map[0], map[1], map[2]))
            }
        }
        return values.min()
    }

    private fun doMappings(mappingRules: MutableList<Triple<Long, Long, Long>>, values: List<Long>): List<Long> {
        val result = mutableListOf<Long>()
        values.forEach {
            result.add(doMapping(mappingRules, it))
        }
        return result
    }

    private fun doMappings2(
        mappingRules: MutableList<Triple<Long, Long, Long>>,
        values: List<Pair<Long, Long>>
    ): List<Pair<Long, Long>> {
        val result = mutableListOf<Pair<Long, Long>>()
        values.forEach {
            var newRange = Pair(-1L, -1L)
            for (i in it.first until it.second) {
                val mappedValue = doMapping(mappingRules, i)
                if (newRange == Pair(-1L, -1L)) {
                    newRange = Pair(mappedValue, mappedValue)
                } else if (newRange.second + 1 == mappedValue) {
                    newRange = Pair(newRange.first, mappedValue)
                } else {
                    result.add(newRange)
                    newRange = Pair(mappedValue, mappedValue)
                }
            }
            result.add(newRange)
        }
        return result
    }

    private fun doMapping(mappingRules: MutableList<Triple<Long, Long, Long>>, value: Long): Long {
        mappingRules.forEach {
            if (it.second <= value && value < it.second + it.third)
                return it.first + value - it.second
        }
        return value
    }

    fun part2(input: List<String>): Long {
        var values = getValuesFromRanges(input)

        val mappingRules = mutableListOf<Triple<Long, Long, Long>>()
        for (i in 3 until input.size) {
            val line = input[i]
            if (line.isBlank() || i == (input.size - 1)) {
                values = doMappings2(mappingRules, values)
                mappingRules.clear()
            } else if (!line.endsWith("map:")) {
                val map = line.split(" ").map { it.toLong() }
                mappingRules.add(Triple(map[0], map[1], map[2]))
            }
        }
        return values.map { it.first }.filter { it >= 0 }.min()
    }

    private fun getValuesFromRanges(input: List<String>): List<Pair<Long, Long>> {
        val values = mutableListOf<Pair<Long, Long>>()
        val inputRanges = input[0].split(" ").filter { !it.contains("seed") }.map { it.trim().toLong() }
        for (i in inputRanges.indices step 2) {
            values.add(Pair(inputRanges[i], inputRanges[i] + inputRanges[i + 1]))
        }
        return values
    }

}