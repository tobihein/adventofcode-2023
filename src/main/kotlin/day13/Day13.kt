package day13

import readInput

class Day13 {
    fun part1(): Int {
        val readInput = readInput("day13/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day13/input")
        return part2(readInput, 1000000L)
    }

    fun part1(input: List<String>): Int {
        val patterns = parseInput(input)
        return patterns.sumOf {
            getResultForPattern(it)
        }
    }

    private fun getResultForPattern(pattern: List<String>): Int {
        val columnMirror = checkColumnsForMirror(pattern)
        if (columnMirror < 0) {
            return 100 * (checkRowsForMirror(pattern) + 1)
        }
        return columnMirror + 1
    }

    private fun checkColumnsForMirror(pattern: List<String>): Int {
        val range = 0..pattern[0].length - 2
        var possibleColumns = range.toList()
        pattern.forEach {
            possibleColumns = getNextPossibleColumns(it, possibleColumns)
        }
        if (possibleColumns.size > 1) {
            println("WARNING, we found too many possible solutios $possibleColumns")
        }
        return if (possibleColumns.isEmpty()) -1 else possibleColumns.first()
    }

    private fun getNextPossibleColumns(line: String, possibleColumns: List<Int>): List<Int> {
        val nextPossibleColumns = mutableListOf<Int>()
        val width = line.length - 1

        possibleColumns.forEach {
            var counter = 1
            var isOk = true
            var loopExecuted = false
            for (column in it downTo 0) {
                if (isOk && it + counter <= width) {
                    loopExecuted = true
                    val right = it + counter
                    val leftChar = line.toCharArray()[column]
                    val rightChar = line.toCharArray()[right]
                    if (leftChar != rightChar) {
                        isOk = false
                    }
                }
                counter++
            }
            if (isOk && loopExecuted) {
                nextPossibleColumns.add(it)
            }
        }
        return nextPossibleColumns
    }

    private fun checkRowsForMirror(pattern: List<String>): Int {
        return checkColumnsForMirror(invertPattern(pattern))
    }

    private fun parseInput(input: List<String>): List<List<String>> {
        val parsedInput = mutableListOf<List<String>>()
        var singlePattern = mutableListOf<String>()
        input.forEach {
            if (it.isBlank()) {
                parsedInput.add(singlePattern)
                singlePattern = mutableListOf<String>()
            } else {
                singlePattern.add(it)
            }
        }
        parsedInput.add(singlePattern)
        return parsedInput
    }

    private fun invertPattern(pattern: List<String>): List<String> {
        val inverted = mutableMapOf<Int, String>()
        pattern.forEach {
            it.toCharArray().forEachIndexed { index, c ->
                if (inverted.contains(index)) {
                    inverted.replace(index, inverted[index] + c.toString())
                } else {
                    inverted[index] = c.toString()
                }
            }
        }
        return inverted.values.toList()
    }

    fun part2(input: List<String>, expand: Long): Int = 0
}