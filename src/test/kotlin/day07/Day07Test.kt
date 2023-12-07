package day07

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day07Test {
    companion object MultiplyParameters {
        @JvmStatic
        fun part1TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "32T3K 765",
                        "T55J5 684",
                        "KK677 28",
                        "KTJJT 220",
                        "QQQJA 483"
                    ), 6440
                )
            )

        @JvmStatic
        fun part2TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "32T3K 765",
                        "T55J5 684",
                        "KK677 28",
                        "KTJJT 220",
                        "QQQJA 483"
                    ), 5905
                )
            )
    }

    @ParameterizedTest
    @MethodSource("part1TestInput")
    fun part1Example(input: List<String>, expected: Int) {
        assertThat(Day07().part1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("part2TestInput")
    fun part2Example(input: List<String>, expected: Int) {
        assertThat(Day07().part2(input)).isEqualTo(expected)
    }

    @Test
    fun part1Solution() {
        val result = Day07().part1()
        println(result)
        assertThat(result).isEqualTo(251545216)
    }

    @Test
    fun part2Solution() {
        val result = Day07().part2()
        println(result)
        assertThat(result).isEqualTo(250384185)
    }
}
