package util.mapper

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import parser.xml.RequestXml
import util.mapper.RequestFromXml

internal class RequestFromXmlTest {

    private val mapper= RequestFromXml()

    @Test
    operator fun invoke() {
        //given
        val requestXml = RequestXml(
                1,
                4,
                "Sample",
                2,
                2.0
        )

        //when
        val request = mapper(requestXml)

        //then
        assertEquals(request.clientId, 1)
        assertEquals(request.requestId, 4)
        assertEquals(request.name, "Sample")
        assertEquals(request.quantity, 2)
        assertEquals(request.price, 2.0)
    }
}