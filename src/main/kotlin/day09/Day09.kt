package day09

import readInput

class Day09 {
    fun part1(): Int {
        val readInput = readInput("day09/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day09/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            getNextElement(it)
        }
    }

    private fun getNextElement(input: String): Int {
        val elements = input.split(" ").map { it.toInt() }
        return getNextElement(elements)
    }

    private fun getNextElement(elements: List<Int>): Int {
        if (elements.toSet().size == 1) {
            return elements[0]
        }
        val nextElements = mutableListOf<Int>()
        for (i in 0 until elements.size - 1) {
            nextElements.add(elements[i + 1] - elements[i])
        }
        return elements[elements.size - 1] + getNextElement(nextElements)
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            getPreviousElement(it)
        }
    }

    private fun getPreviousElement(input: String): Int {
        val elements = input.split(" ").map { it.toInt() }
        return getPreviousElement(elements)
    }

    private fun getPreviousElement(elements: List<Int>): Int {
        if (elements.toSet().size == 1) {
            return elements[0]
        }
        val nextElements = mutableListOf<Int>()
        for (i in 0 until elements.size - 1) {
            nextElements.add(elements[i + 1] - elements[i])
        }
        return elements[0]- getPreviousElement(nextElements)
    }

}