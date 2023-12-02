package template

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TemplateTest {
    companion object MultiplyParameters {
        @JvmStatic
        fun part1TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf("1000"), 0)
            )

        @JvmStatic
        fun part2TestInput(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf("1000"), 0)
            )
    }

    @ParameterizedTest
    @MethodSource("part1TestInput")
    fun part1(input: List<String>, expected: Int) {
        assertThat(Template().part1(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("part2TestInput")
    fun part2(input: List<String>, expected: Int) {
        assertThat(Template().part2(input)).isEqualTo(expected)
    }

    @Test
    fun part1() {
        println(Template().part1())
    }

    @Test
    fun part2() {
        println(Template().part2())
    }
}
