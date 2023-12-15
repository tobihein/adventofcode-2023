package day15

import readInput

class Day15 {
    private val roundRock = 'O'
    private val shapedRock = '#'

    fun part1(): Int {
        val readInput = readInput("day15/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day15/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int {
        val strings = parseInput(input)
        return strings.sumOf {
            getHashValue(it)
        }
    }

    private val boxes = mutableMapOf<Int, MutableList<Pair<String, Int>>>()

    fun part2(input: List<String>): Int {
        val strings = parseInput(input)
        strings.forEach { addToBox(it) }
        return totalFocusingPower()
    }

    private fun totalFocusingPower(): Int {
        var sum=0
        boxes.forEach {
            sum += focusingPowerPerBox(it)
        }
        return sum
    }

    private fun focusingPowerPerBox(box: Map.Entry<Int, MutableList<Pair<String, Int>>>): Int {
        val boxNumber = box.key
        var focusingPower=0
        box.value.forEachIndexed{index, pair ->
            val nextValue = ((boxNumber+1) * (index+1) * pair.second)
            focusingPower += nextValue
        }
        return focusingPower
    }

    private fun addToBox(string: String): Int {
        val parts = string.split("-", "=")
        val boxNumber = getHashValue(parts[0])
        val currentBox = boxes.getOrPut(boxNumber) { mutableListOf<Pair<String, Int>>() }
        if (string.contains("=") ) {
            val index = currentBox.indexOfFirst { it.first==parts[0] }
            if(index >=0) {
                currentBox[index]=Pair(parts[0], parts[1].toInt())
            } else {
                currentBox.add(Pair(parts[0], parts[1].toInt()))
            }
        } else if (string.contains("-")) {
            currentBox.removeIf { it.first == parts[0] }
        }
        return 0;
    }

    private fun parseInput(input: List<String>): List<String> =
        input[0].split(",").toList()

    private fun getHashValue(line: String): Int {
        var previousCode = 0
        line.toCharArray().forEach {
            previousCode = ((previousCode + it.code) * 17 % 256)
        }
        return previousCode
    }

}