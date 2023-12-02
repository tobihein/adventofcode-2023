package template

import readInput

class Template {
    fun part1(): Int {
        val readInput = readInput("template/input.txt")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("template/input.txt")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int = 0

    fun part2(input: List<String>): Int = 0

}
