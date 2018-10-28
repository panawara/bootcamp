package util

import model.Request

/**
 * Filter list by clientId
 * @param clientId Id of the client that we want to keep in the list.
 * @return List of requests which contains only request for provided client id, or all requests if cliendId is ``null``
 */
fun List<Request>.filterByClientId(clientId: Int?): List<Request> {
    return filter { request -> clientId == null || clientId == request.clientId  }
}