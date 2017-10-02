package unit

import org.junit.Assert
import org.junit.Test

class Calculator {
    fun add(first: Int, second: Int): Int {
        return first + second
    }
}

class CalculatorTest {

    @Test
    fun shouldAddNumbers() {
        val calculator = Calculator()
        val result = calculator.add(1, 2)

        Assert.assertEquals(result, 3)
    }

}

