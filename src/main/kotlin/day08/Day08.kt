package day08

import readInput
import java.time.LocalDateTime

class Day08 {
    fun part1(): Int {
        val readInput = readInput("day08/input")
        return part1(readInput)
    }

    fun part2(): Long {
        val readInput = readInput("day08/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int {
        val directions = input[0]
        val start = "AAA"
        val mappings = parse(input.subList(2, input.size))
        val end = "ZZZ"
        var nextTarget = start
        var steps = 0
        do {
            val direction = directions.toCharArray()[steps % directions.length]
            nextTarget = if (direction == 'L') {
                mappings[nextTarget]!!.first
            } else {
                mappings[nextTarget]!!.second

            }
            steps++
        } while (nextTarget != end)
        return steps
    }

    fun part2(input: List<String>): Long {
        val directions = input[0]
        val mappings = parse(input.subList(2, input.size))
        var nextTarget = mappings.keys.filter { it.endsWith("A") }.toSet()
        var steps = 0L
        var stepsIndex = 0
        do {
            stepsIndex = (stepsIndex % directions.length)
            val direction = directions.toCharArray()[stepsIndex]
            if (direction == 'L') {
                nextTarget = nextTarget.map { mappings[it]!!.first }.toSet()
            } else {
                nextTarget = nextTarget.map { mappings[it]!!.second }.toSet()
            }
            if (steps % 10000000L == 0L) {
                println(LocalDateTime.now())
                println("Step $steps:  $nextTarget")
            }
            steps++
            stepsIndex++
        } while (nextTarget.any { !it.endsWith("Z") })
        return steps
    }

    private fun parse(list: List<String>): Map<String, Pair<String, String>> {
        val mapping = mutableMapOf<String, Pair<String, String>>()
        list.forEach {
            val leftRight = it.split("=")
            val key = leftRight[0].trim()
            val values = leftRight[1].replace("(", "").replace(")", "").replace(" ", "").split(",")
            mapping[key] = Pair(values[0], values[1])
        }
        return mapping
    }
}