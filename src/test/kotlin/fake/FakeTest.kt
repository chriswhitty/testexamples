package fake

import org.hamcrest.Matchers.hasSize
import org.junit.Assert.assertThat
import org.junit.Test

interface MessageRepository {
    fun persistMessage(message: String)
    fun getMessages(): List<String>
}

class ChatServer(val messageRepository: MessageRepository) {

    fun messageRecieved(message: String) {
        if (messageRepository.getMessages().contains(message)) {
            return
        }
        messageRepository.persistMessage(message)
    }
}

class FakeMessageRepository() : MessageRepository {
    private val messages = mutableListOf<String>()

    override fun persistMessage(message: String) {
        messages += message
    }
    override fun getMessages(): List<String> {
        return messages
    }
}

class ChatServerTest {

    @Test
    fun `should store messages after removing duplicates`() {
        val repository = FakeMessageRepository()
        val chatServer = ChatServer(repository)

        chatServer.messageRecieved("First message")
        chatServer.messageRecieved("First message")

        assertThat(repository.getMessages(), hasSize(1))
    }
}
