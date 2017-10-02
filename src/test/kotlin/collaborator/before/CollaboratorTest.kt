package collaborator.before

import collaborator.after.History
import java.util.*


class History {
    private val history = Stack<Int>()

    fun pushValue(value: Int) {
        history.push(value)
    }

    fun peekValue(): Int {
        return history.peek()
    }
}

class Calculator {
    private val history = History()

    fun add(a: Int, b: Int): Int {
        val result = a + b
        history.pushValue(result)
        return result
    }
}

