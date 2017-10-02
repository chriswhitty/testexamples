package mock

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

interface UserService {
    fun getUserName(id: Int): String
}

interface AccessLogger {
    fun serviceCalled(service: String, id: Int): String
}

class GreetingService(val userService: UserService, val accessLogger: AccessLogger) {

    fun getGreeting(id: Int): String {
        accessLogger.serviceCalled("Greeting", id)
        val name = userService.getUserName(id)
        return "Welcome $name!"
    }
}


class GreetingServiceTest {

    @Test
    fun `should greet user with their name`() {
        val mockAccessLogger = mock<AccessLogger>()
        val mockUserService = mock<UserService>()
        val greetingService = GreetingService(mockUserService, mockAccessLogger)

        whenever(mockUserService.getUserName(100)).thenReturn("Vivian")

        val greeting = greetingService.getGreeting(100)
        assertThat(greeting, equalTo("Welcome Vivian!"))

        verify(mockAccessLogger, times(1)).serviceCalled("Greeting", 100)
    }
}
