import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

interface Logger {
    fun log(msg: String)
}

class LoggingCalculator(val logger: Logger) {

    fun add(first: Int, second: Int): Int {
        logger.log("Adding: $first + $second")
        return first + second
    }
}

class DummyLogger : Logger {
    override fun log(msg: String) {}
}

class LoggingCalculatorTest {

    @Test
    fun shouldAddNumbers() {
        val calculator = LoggingCalculator(DummyLogger())
        val result = calculator.add(1, 2)

        assertThat(result, equalTo(3))
    }

}
