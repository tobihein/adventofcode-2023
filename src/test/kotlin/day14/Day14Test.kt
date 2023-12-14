package day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day14Test {
    companion object MultiplyParameters {
        @JvmStatic
        fun part1TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "O....#....",
                        "O.OO#....#",
                        ".....##...",
                        "OO.#O....O",
                        ".O.....O#.",
                        "O.#..O.#.#",
                        "..O..#O..O",
                        ".......O..",
                        "#....###..",
                        "#OO..#...."
                    ), 136
                )
            )

        @JvmStatic
        fun part2TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "O....#....",
                        "O.OO#....#",
                        ".....##...",
                        "OO.#O....O",
                        ".O.....O#.",
                        "O.#..O.#.#",
                        "..O..#O..O",
                        ".......O..",
                        "#....###..",
                        "#OO..#...."
                    ), 1, 18
                ),
                Arguments.of(
                    listOf(
                        "O....#....",
                        "O.OO#....#",
                        ".....##...",
                        "OO.#O....O",
                        ".O.....O#.",
                        "O.#..O.#.#",
                        "..O..#O..O",
                        ".......O..",
                        "#....###..",
                        "#OO..#...."
                    ), 2, 18
                ),
                Arguments.of(
                    listOf(
                        "O....#....",
                        "O.OO#....#",
                        ".....##...",
                        "OO.#O....O",
                        ".O.....O#.",
                        "O.#..O.#.#",
                        "..O..#O..O",
                        ".......O..",
                        "#....###..",
                        "#OO..#...."
                    ), 3, 18
                )
            )
    }

    @ParameterizedTest
    @MethodSource("part1TestInput")
    fun part1Example(input: List<String>, expected: Int) {
        assertThat(Day14().part1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("part2TestInput")
    fun part2Example(input: List<String>, cycles: Int, expected: Int) {
        assertThat(Day14().part2(input, cycles)).isEqualTo(expected)
    }

    @Test
    fun part1Solution() {
        val result = Day14().part1()
        println(result)
        assertThat(result).isEqualTo(108641)
    }

    @Test
    fun part2Solution() {
        val result = Day14().part2()
        println(result)
        assertThat(result).isEqualTo(560822911938)
    }
}
