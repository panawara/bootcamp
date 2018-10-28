package model

data class Request(
        val clientId: Int,
        val requestId: Int,
        val name: String,
        val quantity: Int,
        val price: Double
)