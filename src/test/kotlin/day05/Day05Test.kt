package day05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day05Test {
    companion object MultiplyParameters {
        @JvmStatic
        fun part1TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "seeds: 79 14 55 13",
                        "",
                        "seed-to-soil map:",
                        "50 98 2",
                        "52 50 48",
                        "",
                        "soil-to-fertilizer map:",
                        "0 15 37",
                        "37 52 2",
                        "39 0 15",
                        "",
                        "fertilizer-to-water map:",
                        "49 53 8",
                        "0 11 42",
                        "42 0 7",
                        "57 7 4",
                        "",
                        "water-to-light map:",
                        "88 18 7",
                        "18 25 70",
                        "",
                        "light-to-temperature map:",
                        "45 77 23",
                        "81 45 19",
                        "68 64 13",
                        "",
                        "temperature-to-humidity map:",
                        "0 69 1",
                        "1 0 69",
                        "",
                        "humidity-to-location map:",
                        "60 56 37",
                        "56 93 4"
                    ), 35
                )
            )

        @JvmStatic
        fun part2TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "seeds: 79 14 55 13",
                        "",
                        "seed-to-soil map:",
                        "50 98 2",
                        "52 50 48",
                        "",
                        "soil-to-fertilizer map:",
                        "0 15 37",
                        "37 52 2",
                        "39 0 15",
                        "",
                        "fertilizer-to-water map:",
                        "49 53 8",
                        "0 11 42",
                        "42 0 7",
                        "57 7 4",
                        "",
                        "water-to-light map:",
                        "88 18 7",
                        "18 25 70",
                        "",
                        "light-to-temperature map:",
                        "45 77 23",
                        "81 45 19",
                        "68 64 13",
                        "",
                        "temperature-to-humidity map:",
                        "0 69 1",
                        "1 0 69",
                        "",
                        "humidity-to-location map:",
                        "60 56 37",
                        "56 93 4"
                    ), 46
                )
            )
    }

    @ParameterizedTest
    @MethodSource("part1TestInput")
    fun part1Example(input: List<String>, expected: Long) {
        assertThat(Day05().part1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("part2TestInput")
    fun part2Example(input: List<String>, expected: Long) {
        assertThat(Day05().part2(input)).isEqualTo(expected)
    }

    @Test
    fun part1Solution() {
        println(Day05().part1())
    }

    @Test
    fun part2Solution() {
        println(Day05().part2())
    }
}
