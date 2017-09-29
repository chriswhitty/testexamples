import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

interface StorageService {
    fun store(name: String, bytes: ByteArray)
    fun retrieve(name: String): ByteArray?
}

class InMemoryStorageService : StorageService {
    val mapping = mutableMapOf<String, ByteArray>()

    override fun store(name: String, bytes: ByteArray) {
        mapping[name] = bytes
    }

    override fun retrieve(name: String): ByteArray? {
        return mapping[name]
    }
}

class FileStorageService(val directory: Path) : StorageService {

    override fun store(name: String, bytes: ByteArray) {
        Files.createDirectories(directory)
        val fileLocation = directory.resolve(name)
        Files.write(fileLocation, bytes)
    }

    override fun retrieve(name: String): ByteArray? {
        val fileLocation = directory.resolve(name)
        return Files.readAllBytes(fileLocation)
    }
}

abstract class StorageServiceContractTests {

    abstract fun storageService(): StorageService

    @Test
    fun shouldStoreAndRetrieve() {
        val storageService = storageService()
        val storedBytes = "Store these bytes".toByteArray()

        storageService.store("storage-key", storedBytes)
        val retrievedBytes = storageService.retrieve("storage-key")

        assertThat(retrievedBytes, equalTo(storedBytes))
    }
}

class InMemoryStorageServiceTest : StorageServiceContractTests() {
    override fun storageService(): StorageService {
        return InMemoryStorageService()
    }
}

class FileStorageServiceTest : StorageServiceContractTests() {
    override fun storageService(): StorageService {
        return FileStorageService(Paths.get("/tmp/" + UUID.randomUUID()))
    }
}
