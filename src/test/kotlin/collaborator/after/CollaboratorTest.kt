package collaborator.after

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.*

class StackHistory : History {
    private val history = Stack<Int>()

    override fun newValue(value: Int) {
        history.push(value)
    }

    override fun lastValue(): Int {
        return history.peek()
    }
}

interface History {
    fun newValue(value: Int)
    fun lastValue(): Int
}

class Calculator(val history: History) {

    fun add(a: Int, b: Int): Int {
        val result = a + b
        history.newValue(result)
        return result
    }
}


class CalculatorTest {

    @Test
    fun `should store added value in history`() {
        val history = StackHistory()
        val calculator = Calculator(history)

        calculator.add(3, 2)

        assertThat(history.lastValue(), equalTo(5))
    }
}
