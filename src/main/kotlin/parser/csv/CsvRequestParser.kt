package parser.csv

import com.opencsv.bean.CsvToBeanBuilder
import model.Request
import parser.RequestParser
import util.mapper.RequestFromCsv
import java.io.File
import java.io.FileReader

class CsvRequestParser : RequestParser() {

    private val requestFromCsv = RequestFromCsv()

    override fun invoke(file: File): List<Request> {
        val csvToBean = CsvToBeanBuilder<RequestCsv>(FileReader(file))
                .withType(RequestCsv::class.java)
                .withThrowExceptions(false)
                .build()

        val requestCsvList = csvToBean
                .parse()

        csvToBean.capturedExceptions.forEach { csvException ->
            System.err.println("Line number: ${csvException.lineNumber}: ${csvException.localizedMessage}")
        }

        return requestCsvList.map(requestFromCsv)
    }
}