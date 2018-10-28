package util

import parser.xml.RequestXml

class RequestXmlValidator: (Int, RequestXml) -> Boolean {

    override fun invoke(index: Int, requestXml: RequestXml): Boolean {
        val requestNumber = index + 1
        var isValid = true

        if(requestXml.clientId < 0) {
            println("(#$requestNumber) Request hasn't client id.")
            isValid = false
        }

        if(requestXml.requestId < 0) {
            println("(#$requestNumber) Request hasn't request.")
            isValid = false
        }

        if(requestXml.name.isNullOrEmpty()) {
            println("(#$requestNumber) Request hasn't name.")
            isValid = false
        }

        if(requestXml.quantity < 0) {
            println("(#$requestNumber) Request hasn't quantity.")
            isValid = false
        }

        if(requestXml.price < 0) {
            println("(#$requestNumber) Request hasn't valid price.")
            isValid = false
        }

        if(isValid.not()){
            println("(#$requestNumber) Request skipped\n")
        }
        return isValid
    }
}