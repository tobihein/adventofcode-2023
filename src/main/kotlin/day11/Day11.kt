package day11

import readInput
import kotlin.math.abs

class Day11 {
    fun part1(): Long {
        val readInput = readInput("day11/input")
        return part1(readInput)
    }

    fun part2(): Long {
        val readInput = readInput("day11/input")
        return part2(readInput, 1000000L)
    }

    fun part1(input: List<String>): Long {
        val positions = getPositions(input, 2L)
        var sumOfDistances = 0L
        positions.keys.forEach {
            sumOfDistances += getSumOfDistanceToOthers(it, positions)
        }
        return sumOfDistances
    }

    private fun getSumOfDistanceToOthers(currentIndex: Int, positions: Map<Int, Pair<Long, Long>>): Long {
        var sum = 0L
        for (other in currentIndex + 1..positions.size) {
            val current = positions[currentIndex]
            val next = positions[other]
            val distance = abs(current!!.second - next!!.second) + abs(current.first - next.first)
            sum += distance
        }
        return sum
    }

    fun part2(input: List<String>, expand: Long): Long {
        val positions = getPositions(input, expand)
        var sumOfDistances = 0L
        positions.keys.forEach {
            sumOfDistances += getSumOfDistanceToOthers(it, positions)
        }
        return sumOfDistances
    }

    private fun getPositions(input: List<String>, expand: Long): Map<Int, Pair<Long, Long>> {
        val columnMapper = getColumnMapper(input, expand)
        var lineCounter = 0L
        val positions = mutableMapOf<Int, Pair<Long, Long>>()
        var index = 1
        input.forEach {
            if (hasNoGalaxy(it)) {
                lineCounter += (expand - 1L)
            } else {
                val lineArray = it.toCharArray()
                for (i in lineArray.indices) {
                    if (lineArray[i] == '#') {
                        positions[index] = Pair(columnMapper[i]!!, lineCounter)
                        index++
                    }
                }
            }
            lineCounter++
        }
        return positions
    }

    private fun getColumnMapper(input: List<String>, expand: Long): Map<Int, Long> {
        var additional = 0L
        val mappings = mutableMapOf<Int, Long>()
        input[0].toCharArray().forEachIndexed { idx, v ->
            if (v == '.' && isEmptyColumn(idx, input)) {
                additional += (expand-1)
            }
            mappings[idx] = idx + additional
        }
        return mappings
    }

    private fun isEmptyColumn(idx: Int, input: List<String>): Boolean {
        input.forEach {
            if (it.toCharArray()[idx] == '#') return false
        }
        return true
    }


    private fun hasNoGalaxy(line: String): Boolean = line.toCharArray().all { it == '.' }
}