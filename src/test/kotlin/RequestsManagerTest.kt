import model.Result
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File
import java.io.FileNotFoundException

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RequestsManagerTest {

    private lateinit var requestsManager: RequestsManager
    private lateinit var requestsFile: File

    @BeforeAll
    fun prepareFiles() {
        requestsFile = File("src/test/resources/requests.xml")
    }

    @Test
    fun count() {
        //given
        val builder = RequestsManager.Builder()
        builder.sourceFile = requestsFile.path
        builder.action = Action.COUNT.name
        requestsManager = builder.build()

        //when
        val results = requestsManager.start()

        //then
        when (results) {
            is Result.Count -> Assertions.assertEquals(4, results.count)
        }
    }

    @Test
    fun sum() {
        //given
        val builder = RequestsManager.Builder()
        builder.sourceFile = requestsFile.path
        builder.action = Action.SUM.name
        requestsManager = builder.build()

        //when
        val results = requestsManager.start()

        //then
        when (results) {
            is Result.Sum -> Assertions.assertEquals(50.0, results.sum)
        }
    }

    @Test
    fun list() {
        //given
        val builder = RequestsManager.Builder()
        builder.sourceFile = requestsFile.path
        builder.action = Action.LIST.name
        requestsManager = builder.build()

        //when
        val results = requestsManager.start()

        //then
        when (results) {
            is Result.List -> Assertions.assertTrue { results.requestList.isNotEmpty() }
        }
    }

    @Test
    fun avg() {
        //given
        val builder = RequestsManager.Builder()
        builder.sourceFile = requestsFile.path
        builder.action = Action.AVG.name
        requestsManager = builder.build()

        //when
        val results = requestsManager.start()

        //then
        when (results) {
            is Result.Avg -> Assertions.assertEquals(12.5, results.avg)
        }
    }

    @Test
    fun filterClient1() {
        //given
        val builder = RequestsManager.Builder()
        builder.sourceFile = requestsFile.path
        builder.action = Action.SUM.name
        builder.clientId = "1"
        requestsManager = builder.build()

        //when
        val results = requestsManager.start()

        //then
        when (results) {
            is Result.Sum -> Assertions.assertEquals(40.0, results.sum)
        }
    }

    @Test
    fun filterClient2() {
        //given
        val builder = RequestsManager.Builder()
        builder.sourceFile = requestsFile.path
        builder.action = Action.SUM.name
        builder.clientId = "2"
        requestsManager = builder.build()

        //when
        val results = requestsManager.start()

        //then
        when (results) {
            is Result.Sum -> Assertions.assertEquals(10.0, results.sum)
        }
    }

    @Test
    fun wrongAction() {
        //given
        val builder = RequestsManager.Builder()
        builder.sourceFile = requestsFile.path
        builder.action = "Wrong Action"
        requestsManager = builder.build()

        //then
        Assertions.assertThrows(IllegalArgumentException::class.java) { requestsManager.start() }
    }

    @Test
    fun wrongFile() {
        //given
        val builder = RequestsManager.Builder()
        builder.sourceFile = "WrongFile.xml"
        builder.action = Action.SUM.name
        requestsManager = builder.build()

        //then
        Assertions.assertThrows(FileNotFoundException::class.java) { requestsManager.start() }
    }
}