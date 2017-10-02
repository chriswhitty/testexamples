package stub

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

interface ChatClient {
    fun getMessages(): List<String>
}

class ChatService(val chatClient: ChatClient) {

    fun getFirstMessage(): String {
        return chatClient.getMessages().first()
    }
}

class StubChatClient(val messagesToReturn: List<String>) : ChatClient {
    override fun getMessages(): List<String> {
        return messagesToReturn
    }
}

class ChatClientTest {

    @Test
    fun `should get the first message`() {
        val stubChatClient = StubChatClient(listOf("first message", "second message"))
        val chatService = ChatService(stubChatClient)

        assertThat(chatService.getFirstMessage(), equalTo("first message"))
    }
}
