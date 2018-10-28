package util.mapper

import model.Request
import parser.xml.RequestXml

class RequestFromXml : (RequestXml) -> Request {

    override fun invoke(requestXml: RequestXml): Request {
        return with(requestXml) {
            Request(
                    clientId,
                    requestId,
                    requireNotNull(name),
                    quantity,
                    price
            )
        }
    }
}