package day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day10Test {
    companion object MultiplyParameters {
        @JvmStatic
        fun part1TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        ".....",
                        ".S-7.",
                        ".|.|.",
                        ".L-J.",
                        "....."
                    ), 4
                ),
                Arguments.of(
                    listOf(
                        "..F7.",
                        ".FJ|.",
                        "SJ.L7",
                        "|F--J",
                        "LJ..."
                    ), 8
                )
            )

        @JvmStatic
        fun part2TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        ".....",
                        ".S-7.",
                        ".|.|.",
                        ".L-J.",
                        "....."
                    ), 4
                ),
                Arguments.of(
                    listOf(
                        "..F7.",
                        ".FJ|.",
                        "SJ.L7",
                        "|F--J",
                        "LJ..."
                    ), 8
                )
            )
    }

    @ParameterizedTest
    @MethodSource("part1TestInput")
    fun part1Example(input: List<String>, expected: Int) {
        assertThat(Day10().part1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("part2TestInput")
    fun part2Example(input: List<String>, expected: Int) {
        assertThat(Day10().part2(input)).isEqualTo(expected)
    }

    @Test
    fun part1Solution() {
        val result = Day10().part1()
        println(result)
        assertThat(result).isEqualTo(2008960228)
    }

    @Test
    fun part2Solution() {
        val result = Day10().part2()
        println(result)
        assertThat(result).isEqualTo(1097)
    }
}
