package util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import parser.xml.RequestXml

internal class RequestXmlValidatorTest {

    private val validator = RequestXmlValidator()
    private lateinit var requestXml: RequestXml

    @BeforeEach
    fun prepareRequestXml() {
        requestXml = RequestXml(
                1,
                2,
                "Test",
                23,
                10.0
        )
    }

    @Test
    fun validRequestXml() {
        Assertions.assertTrue(validator(0, requestXml))
    }

    @Test
    fun clientIdValidation() {
        Assertions.assertFalse(validator(0, requestXml.copy(
                clientId = -1
        )))
    }

    @Test
    fun requestIdValidation() {
        Assertions.assertFalse(validator(0, requestXml.copy(
                requestId = -1
        )))
    }

    @Test
    fun nameValidation() {
        Assertions.assertFalse(validator(0, requestXml.copy(
                name = null
        )))
    }

    @Test
    fun quantityValidation() {
        Assertions.assertFalse(validator(0, requestXml.copy(
                quantity = -1
        )))
    }

    @Test
    fun priceValidation() {
        Assertions.assertFalse(validator(0, requestXml.copy(
                price = -1.0
        )))
    }
}