package parser.xml

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "requests")
class RequestListXml {
    @XmlElement(name = "request")
    val requestList: List<RequestXml> = mutableListOf()
}