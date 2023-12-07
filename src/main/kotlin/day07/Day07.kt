package day07

import readInput

class Day07 {
    fun part1(): Int {
        val readInput = readInput("day07/input")
        return part1(readInput)
    }

    fun part2(): Int {
        val readInput = readInput("day07/input")
        return part2(readInput)
    }

    fun part1(input: List<String>): Int {
        val parsedInput = parseInput(input)
        val sorted = parsedInput.sortedWith(
            compareBy(this::getType)
                .then(compareBy(this::getFirstChar))
                .then(compareBy(this::getSecondChar))
                .then(compareBy(this::getThirdChar))
                .then(compareBy(this::getFourthChar))
                .then(compareBy(this::getFifthChar))
        )
        var sum = 0
        sorted.forEachIndexed { idx, value ->
            sum += (idx + 1) * value.third
        }
        return sum
    }

    private fun getFirstChar(triple: Triple<Int, List<Int>, Int>): Comparable<*> = triple.second[0]

    private fun getSecondChar(triple: Triple<Int, List<Int>, Int>): Comparable<*> = triple.second[1]

    private fun getThirdChar(triple: Triple<Int, List<Int>, Int>): Comparable<*> = triple.second[2]

    private fun getFourthChar(triple: Triple<Int, List<Int>, Int>): Comparable<*> = triple.second[3]

    private fun getFifthChar(triple: Triple<Int, List<Int>, Int>): Comparable<*> = triple.second[4]

    private fun getType(triple: Triple<Int, List<Int>, Int>): Comparable<*> = triple.first


    fun part2(input: List<String>): Int {
        val parsedInput = parseInput2(input)
        val sorted = parsedInput.sortedWith(
            compareBy(this::getType)
                .then(compareBy(this::getFirstChar))
                .then(compareBy(this::getSecondChar))
                .then(compareBy(this::getThirdChar))
                .then(compareBy(this::getFourthChar))
                .then(compareBy(this::getFifthChar))
        )
        var sum = 0
        sorted.forEachIndexed { idx, value ->
            sum += (idx + 1) * value.third
        }
        return sum
    }

    private fun parseInput(input: List<String>): List<Triple<Int, List<Int>, Int>> =
        input.map { addType(it) }.toList()

    private fun parseInput2(input: List<String>): List<Triple<Int, List<Int>, Int>> =
        input.map { addType2(it) }.toList()

    private fun addType(line: String): Triple<Int, List<Int>, Int> {
        val parts = line.split(" ")
        val deck = parts[0]
        val bid = parts[1].toInt()
        val strength = getDeckStrength(deck)
        return Triple(strength, toComparableList(deck), bid)
    }

    private fun addType2(line: String): Triple<Int, List<Int>, Int> {
        val parts = line.split(" ")
        val deck = parts[0]
        val bid = parts[1].toInt()
        val strength = getDeckStrength2(deck)
        val name = toStrengthName(strength)
        return Triple(strength, toComparableList2(deck), bid)
    }

    private fun toStrengthName(strength: Int): String {
        return when (strength) {
            0 -> "High Card"
            1 -> "One Pair"
            2 -> "Two Pairs"
            3 -> "Three of a kind"
            4 -> "Full House"
            5 -> "Four of a kind"
            6 -> "Five of a kind"
            else -> "?????"
        }
    }

    private fun toComparableList(deck: String): List<Int> = deck.toCharArray().map { toNumber(it) }.toList()
    private fun toComparableList2(deck: String): List<Int> = deck.toCharArray().map { toNumber2(it) }.toList()


    private fun toNumber(char: Char): Int {
        return when (char) {
            'T' -> 10
            'J' -> 11
            'Q' -> 12
            'K' -> 13
            'A' -> 14
            else -> char.digitToInt()
        }
    }

    private fun toNumber2(char: Char): Int {
        return when (char) {
            'T' -> 10
            'J' -> 1
            'Q' -> 12
            'K' -> 13
            'A' -> 14
            else -> char.digitToInt()
        }
    }

    private fun getDeckStrength(deck: String): Int {
        if (isHighCard(deck)) {
            return 0
        }
        if (isOnePair(deck)) {
            return 1
        }
        if (isTwoPairs(deck)) {
            return 2
        }
        if (isThreeOfAKind(deck)) {
            return 3
        }
        if (isFullHouse(deck)) {
            return 4
        }
        if (isFourOfAKind(deck)) {
            return 5
        }
        if (isFiveOfAKind(deck)) {
            return 6
        }
        return -1
    }

    private fun getDeckStrength2(deck: String): Int {
        val jokers = deck.count { it == 'J' }
        val withoutJokers = deck.replace("J", "")
        if (isHighCard(withoutJokers)) {
            return when (jokers) {
                2 -> 3
                3 -> 5
                4 -> 6
                5 -> 6
                else -> jokers
            }
        }
        if (isOnePair(withoutJokers)) {
            return when (jokers) {
                1 -> 3
                2 -> 5
                3 -> 6
                else -> 1

            }
        }
        if (isTwoPairs(withoutJokers)) {
            return when (jokers) {
                1 -> 4
                else -> 2
            }
        }
        if (isThreeOfAKind(withoutJokers)) {
            return when (jokers) {
                1 -> 5
                2 -> 6
                else -> 3
            }
        }
        if (isFullHouse(withoutJokers)) {
            return 4
        }
        if (isFourOfAKind(withoutJokers)) {
            return 5 + jokers
        }
        if (isFiveOfAKind(withoutJokers)) {
            return 6
        }
        return -1
    }


    private fun isHighCard(deck: String): Boolean =
        deck.toCharArray().toSet().size == deck.length

    private fun isOnePair(deck: String): Boolean =
        deck.toCharArray().toSet().size == (deck.length - 1)

    private fun isTwoPairs(deck: String): Boolean {
        if (deck.length >= 4) {
            val amounts = deck.toCharArray().toList().groupingBy { it }.eachCount().map { it.value }.sortedDescending()
            return amounts.size >= 2 && amounts[0] == 2 && amounts[1] == 2
        }
        return false
    }

    private fun isThreeOfAKind(deck: String): Boolean {
        if (deck.length >= 3) {
            val amounts = deck.toCharArray().toList().groupingBy { it }.eachCount().map { it.value }.sortedDescending()
            return amounts[0] == 3 && (amounts.size == 3 || deck.length < 5)
        }
        return false
    }

    private fun isFullHouse(deck: String): Boolean {
        if (deck.length == 5) {
            val cards = deck.toCharArray()
            if (cards.toSet().size == 2) {
                val amountFirstCard = cards.count { it == cards[0] }
                return (amountFirstCard == 3 || amountFirstCard == 2)
            }
        }
        return false
    }

    private fun isFourOfAKind(deck: String): Boolean {
        if (deck.length >= 4) {
            val cards = deck.toCharArray()
            if (cards.toSet().size == 1 && deck.length == 4) {
                return true
            }
            if (cards.toSet().size == 2 && deck.length == 5) {
                val amountFirstCard = cards.count { it == cards[0] }
                return cards.size >= 4 && (amountFirstCard == 1 || amountFirstCard == 4)
            }
        }
        return false
    }

    private fun isFiveOfAKind(deck: String): Boolean =
        deck.length == 5 && deck.toCharArray().toSet().size == 1
}