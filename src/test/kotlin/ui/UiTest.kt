package ui

import io.writetests.StaticServer
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver


class UiTest {

    lateinit var driver: WebDriver
    lateinit var server: StaticServer


    @Before
    fun setUp() {
        val port = freePort()
        this.driver = ChromeDriver()
        this.server = StaticServer(port)
                .start()
                .waitForStartUp()
        driver.navigate().to("http://localhost:$port")
    }

    @After
    fun tearDown() {
        server.stop()
        driver.close()
    }

    @Test
    fun `should navigate to index page`() {
        assertThat(driver.pageSource, containsString("<h1>Welcome</h1>"))

    }

}