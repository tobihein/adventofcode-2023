package day08

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day08Test {
    companion object MultiplyParameters {
        @JvmStatic
        fun part1TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "RL",
                        "",
                        "AAA = (BBB, CCC)",
                        "BBB = (DDD, EEE)",
                        "CCC = (ZZZ, GGG)",
                        "DDD = (DDD, DDD)",
                        "EEE = (EEE, EEE)",
                        "GGG = (GGG, GGG)",
                        "ZZZ = (ZZZ, ZZZ)"
                    ), 2
                ),
                Arguments.of(
                    listOf(
                        "LLR",
                        "",
                        "AAA = (BBB, BBB)",
                        "BBB = (AAA, ZZZ)",
                        "ZZZ = (ZZZ, ZZZ)"
                    ), 6
                )
            )

        @JvmStatic
        fun part2TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "LR",
                        "",
                        "11A = (11B, XXX)",
                        "11B = (XXX, 11Z)",
                        "11Z = (11B, XXX)",
                        "22A = (22B, XXX)",
                        "22B = (22C, 22C)",
                        "22C = (22Z, 22Z)",
                        "22Z = (22B, 22B)",
                        "XXX = (XXX, XXX)"
                    ), 6L
                )
            )
    }

    @ParameterizedTest
    @MethodSource("part1TestInput")
    fun part1Example(input: List<String>, expected: Int) {
        assertThat(Day08().part1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("part2TestInput")
    fun part2Example(input: List<String>, expected: Long) {
        assertThat(Day08().part2(input)).isEqualTo(expected)
    }

    @Test
    fun part1Solution() {
        val result = Day08().part1()
        println(result)
        assertThat(result).isEqualTo(12643)
    }

    @Test
    fun part2Solution() {
        val result = Day08().part2()
        println(result)
        assertThat(result).isEqualTo(250384185L)
    }
}
