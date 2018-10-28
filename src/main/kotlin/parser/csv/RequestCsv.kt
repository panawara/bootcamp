package parser.csv

import com.opencsv.bean.CsvBindByName

data class RequestCsv
@JvmOverloads constructor(

        @CsvBindByName(column = "Client_Id")
        val clientId: Int = -1,

        @CsvBindByName(column = "Request_id")
        val requestId: Int = -1,

        @CsvBindByName(column = "Name")
        val name: String? = null,

        @CsvBindByName(column = "Quantity")
        val quantity: Int = -1,

        @CsvBindByName(column = "Price")
        val price: Double = -1.0
)