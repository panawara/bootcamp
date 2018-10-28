package util.mapper

import model.Request
import parser.csv.RequestCsv

class RequestFromCsv : (RequestCsv) -> Request {

    override fun invoke(requestCsv: RequestCsv): Request {
        return with(requestCsv) {
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