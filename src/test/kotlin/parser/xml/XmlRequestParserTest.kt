package parser.xml

import model.Request
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File
import javax.xml.bind.UnmarshalException

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class XmlRequestParserTest {

    private lateinit var emptyFile: File
    private lateinit var requestsFile: File
    private lateinit var noRequestsFile: File
    private val xmlParser = XmlRequestParser()

    @BeforeAll
    fun prepareFiles() {
        emptyFile = File("src/test/resources/empty.xml")
        requestsFile = File("src/test/resources/requests.xml")
        noRequestsFile = File("src/test/resources/no_requests.xml")
    }

    @Test
    fun shouldThrowExceptionIfXmlFileIsWrong() {
        //then
        Assertions.assertThrows(UnmarshalException::class.java) {
            xmlParser(emptyFile)
        }
    }

    @Test
    fun shouldReturnListOfRequestsIfFileIsCorrect() {
        //when
        val parsedRequests = xmlParser(requestsFile)

        //then
        Assertions.assertTrue { parsedRequests.isNotEmpty() }
    }

    @Test
    fun shouldReturnEmptyListIfThereIsNoRequests() {
        //when
        val parsedRequests = xmlParser(noRequestsFile)

        //then
        Assertions.assertIterableEquals(emptyList<Request>(), parsedRequests)
    }
}