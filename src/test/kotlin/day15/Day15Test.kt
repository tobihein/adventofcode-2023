package day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day15Test {
    companion object MultiplyParameters {
        @JvmStatic
        fun part1TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "HASH"
                    ), 52
                ),
                Arguments.of(
                    listOf(
                        "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
                    ), 1320
                )
            )

        @JvmStatic
        fun part2TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
                    ), 145
                )
            )
    }

    @ParameterizedTest
    @MethodSource("part1TestInput")
    fun part1Example(input: List<String>, expected: Int) {
        assertThat(Day15().part1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("part2TestInput")
    fun part2Example(input: List<String>, expected: Int) {
        assertThat(Day15().part2(input)).isEqualTo(expected)
    }

    @Test
    fun part1Solution() {
        val result = Day15().part1()
        println(result)
        assertThat(result).isEqualTo(510273)
    }

    @Test
    fun part2Solution() {
        val result = Day15().part2()
        println(result)
        assertThat(result).isEqualTo(212449)
    }
}
