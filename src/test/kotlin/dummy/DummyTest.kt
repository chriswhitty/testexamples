package dummy

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

interface Logger {
    fun log(msg: String)
}

class ClientVersionChecker(val logger: Logger) {

    fun isVersionValid(major: Int, minor: Int, patch:Int): Boolean {
        logger.log("Checking version $major.$minor.$patch")
        return major >= 3
    }
}

class DummyLogger : Logger {
    override fun log(msg: String) {}
}

class ClientVersionCheckerTest {

    @Test
    fun shouldAllowLatestMajorVersion() {
        val clientVersionChecker = ClientVersionChecker(DummyLogger())

        val versionValid = clientVersionChecker.isVersionValid(3, 2, 2)

        assertThat(versionValid, equalTo(true))
    }

}
