package day14

import readInput

class Day14 {
    private val roundRock = 'O'
    private val shapedRock = '#'

    fun part1(): Int {
        val readInput = readInput("day14/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day14/input")
        return part2(readInput, 1000000000)
    }

    fun part1(input: List<String>): Int {
        var totalLoad = 0
        val currentValue = mutableMapOf<Int, Int>()
        val maxValue = input.size
        input.forEachIndexed { idx, it ->
            val line = it.toCharArray()
            for (column in line.indices) {
                var columnValue = currentValue.getOrPut(column) { maxValue }
                if (line[column] == roundRock) {
                    totalLoad += columnValue
                    columnValue--
                } else if (line[column] == shapedRock) {
                    columnValue = maxValue - idx - 1
                }
                currentValue[column] = columnValue
            }
        }
        return totalLoad
    }

    private val printCycle = 1000
    fun part2(input: List<String>, cycles: Int): Int {
        var next = parseInput(input)
        printArray("before", next)
        for (i in 0 until cycles) {
            val previous = next
            next = rollNorth(next)
            next = rollWest(next)
            next = rollSouth(next)
            next = rollEast(next)
            if (i % printCycle == 0) {
                val cycly = i / printCycle
                println("Current cycle in millions: $cycly")
            }
            if (isEqualArray(i, previous, next)) {
                break
            }
        }
        return getLoadValue(next)
    }

    private fun isEqualArray(index: Int, first: Array<CharArray>, second: Array<CharArray>): Boolean {
        for (i in first.indices) {
            if (first[i].joinToString("", "", "") != second[i].joinToString("", "", "")) {
                return false
            }
        }
        return true
    }

    private fun parseInput(input: List<String>): Array<CharArray> {
        val width = input[0].length
        val height = input.size
        val result = Array(height) { CharArray(width) }
        input.forEachIndexed { row, line ->
            for (column in line.toCharArray().indices) {
                result[row][column] = line[column]
            }
        }
        return result
    }

    private fun rollNorth(input: Array<CharArray>): Array<CharArray> {
        val width = input[0].size
        val height = input.size
        val result = Array(height) { CharArray(width) }
        for (column in 0 until height) {
            var nextFreeRow = 0
            for (row in 0 until width) {
                val currentChar = input[row][column]
                result[row][column] = currentChar
                if (currentChar == roundRock) {
                    result[row][column] = '.'
                    result[nextFreeRow][column] = roundRock
                    nextFreeRow++
                } else if (currentChar == shapedRock) {
                    nextFreeRow = row + 1
                }
            }
        }
        printArray("rollNorth", result)
        return result
    }

    private fun printArray(name: String, array: Array<CharArray>): Unit {
//        println("")
//        println("++++++++ after $name +++++++")
//        for (i in array.indices) {
//            for (j in array[i].indices) {
//                print(array[i][j])
//            }
//            println("")
//        }
//        println("")
    }

    private fun rollWest(input: Array<CharArray>): Array<CharArray> {
        val width = input[0].size
        val height = input.size
        val result = Array(height) { CharArray(width) }
        for (row in 0 until width) {
            var nextFreeColumn = 0
            for (column in 0 until width) {
                val currentChar = input[row][column]
                result[row][column] = currentChar
                if (currentChar == roundRock) {
                    result[row][column] = '.'
                    result[row][nextFreeColumn] = roundRock
                    nextFreeColumn++
                } else if (currentChar == shapedRock) {
                    nextFreeColumn = column + 1
                }
            }
        }
        printArray("rollWest", result)
        return result
    }

    private fun rollSouth(input: Array<CharArray>): Array<CharArray> {
        val width = input[0].size
        val height = input.size
        val result = Array(height) { CharArray(width) }
        for (column in 0 until height) {
            var nextFreeRow = height - 1
            for (row in width - 1 downTo 0) {
                val currentChar = input[row][column]
                result[row][column] = currentChar
                if (currentChar == roundRock) {
                    result[row][column] = '.'
                    result[nextFreeRow][column] = roundRock
                    nextFreeRow--
                } else if (currentChar == shapedRock) {
                    nextFreeRow = row - 1
                }
            }
        }
        printArray("rollSouth", result)
        return result
    }

    private fun rollEast(input: Array<CharArray>): Array<CharArray> {
        val width = input[0].size
        val height = input.size
        val result = Array(height) { CharArray(width) }
        for (row in 0 until width) {
            var nextFreeColumn = width - 1
            for (column in width - 1 downTo 0) {
                val currentChar = input[row][column]
                result[row][column] = currentChar
                if (currentChar == roundRock) {
                    result[row][column] = '.'
                    result[row][nextFreeColumn] = roundRock
                    nextFreeColumn--
                } else if (currentChar == shapedRock) {
                    nextFreeColumn = column - 1
                }
            }
        }
        printArray("rollEast", result)
        return result
    }

    private fun getLoadValue(input: Array<CharArray>): Int {
        var result = 0
        for (row in input.indices) {
            for (column in input[row].indices) {
                val currentChar = input[row][column]
                if (currentChar == roundRock) {
                    result++
                }
            }
        }
        return result
    }
}