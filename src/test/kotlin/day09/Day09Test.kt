package day09

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day09Test {
    companion object MultiplyParameters {
        @JvmStatic
        fun part1TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "0 3 6 9 12 15",
                        "1 3 6 10 15 21",
                        "10 13 16 21 30 45"
                    ), 114
                )
            )

        @JvmStatic
        fun part2TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "0 3 6 9 12 15",
                        "1 3 6 10 15 21",
                        "10 13 16 21 30 45"
                    ), 2
                )
            )
    }

    @ParameterizedTest
    @MethodSource("part1TestInput")
    fun part1Example(input: List<String>, expected: Int) {
        assertThat(Day09().part1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("part2TestInput")
    fun part2Example(input: List<String>, expected: Int) {
        assertThat(Day09().part2(input)).isEqualTo(expected)
    }

    @Test
    fun part1Solution() {
        val result = Day09().part1()
        println(result)
        assertThat(result).isEqualTo(2008960228)
    }

    @Test
    fun part2Solution() {
        val result = Day09().part2()
        println(result)
        assertThat(result).isEqualTo(1097)
    }
}
