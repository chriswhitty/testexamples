package spy

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

interface Display {
    fun displayMessage(message: String)
}

class ChatListener(val display: Display) {

    fun messageRecieved(message: String) {
        display.displayMessage(message)
    }
}

class SpyDisplay() : Display {

    val messages = mutableListOf<String>()

    override fun displayMessage(message: String) {
        messages += message
    }

}

class ChatListenerTest {

    @Test
    fun `should display message to user`() {
        val display = SpyDisplay()
        val chatListener = ChatListener(display)

        chatListener.messageRecieved("display this")

        val firstMessage = display.messages.first()
        assertThat(firstMessage, equalTo("display this"))
    }
}
