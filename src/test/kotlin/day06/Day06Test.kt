package day06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day06Test {
    companion object MultiplyParameters {
        @JvmStatic
        fun part1TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "Time:      7  15   30",
                        "Distance:  9  40  200"
                    ), 288
                )
            )

        @JvmStatic
        fun part2TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "Time:      7  15   30",
                        "Distance:  9  40  200"
                    ), 71503
                )
            )
    }

    @ParameterizedTest
    @MethodSource("part1TestInput")
    fun part1Example(input: List<String>, expected: Int) {
        assertThat(Day06().part1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("part2TestInput")
    fun part2Example(input: List<String>, expected: Int) {
        assertThat(Day06().part2(input)).isEqualTo(expected)
    }

    @Test
    fun part1Solution() {
        println(Day06().part1())
    }

    @Test
    fun part2Solution() {
        println(Day06().part2())
    }
}
