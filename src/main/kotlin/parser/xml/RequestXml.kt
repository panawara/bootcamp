package parser.xml

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

@XmlAccessorType(XmlAccessType.FIELD)
data class RequestXml
@JvmOverloads constructor(
        val clientId: Int = -1,
        val requestId: Int = -1,
        val name: String? = null,
        val quantity: Int = -1,
        val price: Double = -1.0
)