package parser.xml

import model.Request
import parser.RequestParser
import util.mapper.RequestFromXml
import util.RequestXmlValidator
import java.io.File
import javax.xml.bind.JAXBContext

class XmlRequestParser : RequestParser() {

    private val requestXmlToRequest = RequestFromXml()
    private val requestValidator = RequestXmlValidator()

    override fun invoke(file: File): List<Request> {
        val jc = JAXBContext.newInstance(RequestListXml::class.java)
        val unmarshaller = jc.createUnmarshaller()
        val requestListXml = unmarshaller.unmarshal(file) as RequestListXml

        return requestListXml.requestList
                .asSequence()
                .filterIndexed(requestValidator)
                .map(requestXmlToRequest)
                .toList()
    }
}