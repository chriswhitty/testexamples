package mock

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
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

class ChatClientTest {

    @Test
    fun `should get the first message`() {
        val chatClient = mock<ChatClient>()
        val chatService = ChatService(chatClient)

        whenever(chatClient.getMessages()).thenReturn(listOf("first message", "second message"))

        assertThat(chatService.getFirstMessage(), equalTo("first message"))
    }
}