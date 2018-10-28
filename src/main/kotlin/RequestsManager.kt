import Action.*
import model.Request
import model.Result
import parser.csv.CsvRequestParser
import parser.RequestParser
import parser.xml.XmlRequestParser
import util.filterByClientId
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class RequestsManager
//klasa odpowiedzialna za zapytania
private constructor(
        private val sourceFilePath: String,
        private val action: String,
        private val clientId: Int?) {

    fun start(): Result {
        val sourceFile = File(sourceFilePath)
        val actionEnum = mapToAction(action)
        val filteredRequests = getValidRequests(sourceFile)

        return runAction(actionEnum, filteredRequests)
    }

    private fun
    mapToAction(action: String): Action =
            try {
                valueOf(action.toUpperCase())
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException("Available actions: ${Arrays.toString(values())}")
            }

    private fun getValidRequests(sourceFile: File): List<Request> {
        if (!sourceFile.exists()) {
            throw FileNotFoundException("File ${sourceFile.path} not found!")
        }
        val requestParser = getRequestParser(sourceFile.extension)
        return requestParser(sourceFile)
                .filterByClientId(clientId)
    }

    private fun getRequestParser(fileExtension: String): RequestParser =
            when (fileExtension) {
                "xml" -> XmlRequestParser()
                "csv" -> CsvRequestParser()
                else -> throw IllegalArgumentException()
            }

    private fun runAction(action: Action, requestList: List<Request>): Result {
        return when (action) {
            COUNT -> Result.Count(requestList.size)
            SUM -> Result.Sum(requestList.sumByDouble { it.price })
            LIST -> Result.List(requestList)
            AVG -> Result.Avg(requestList.map { it.price }.average())
        }
    }

    class Builder {
        var sourceFile: String? = null
        var action: String? = null
        var clientId: String? = null

        fun build(): RequestsManager {

            return RequestsManager(
                    requireNotNull(sourceFile) { "Source file not set. Use [-s <path>] [--source <path>]" },
                    requireNotNull(action) { "Action not selected. Use [-a <arg>] [--action <arg>]" },
                    clientId?.toIntOrNull()
            )
        }
    }
}