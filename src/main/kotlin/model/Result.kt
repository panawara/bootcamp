package model

sealed class Result {
//wyniki
    data class Count(val count: Int) : Result()
    data class Sum(val sum: Double) : Result()
    data class List(val requestList: kotlin.collections.List<Request>) : Result()
    data class Avg(val avg: Double) : Result()
}