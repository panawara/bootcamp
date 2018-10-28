import model.Result

fun main(vararg args: String) {
    val iterator = args.iterator()
    // robimy pętle

    val builder = RequestsManager.Builder()
    iterator.forEachRemaining {
    // dla każdego, kolejno wykonujemy akcję
        when (it) {
            "-s",
            "--source" -> builder.sourceFile = iterator.next()
            "-a",
            "--action" -> builder.action = iterator.next()
            "-c",
            "--client" -> builder.clientId = iterator.next()
            else -> {
                printAvailableCommands()
            }
        }
    }

    try {
    //jeżeli się nie uda spróbuj
        val results = builder.build().start()
        when (results) {
            is Result.Count -> println("Requests count: ${results.count}")
            is Result.Sum -> println("Requests prices sum: ${results.sum}")
            is Result.List -> println("Requests list: ${results.requestList}")
            is Result.Avg -> println("Requests prices avg: ${results.avg}")
        }
    } catch (e: Exception) {
        println(e.localizedMessage)
    }
}

fun printAvailableCommands() {
    println("Available commands:\n" +
            "[-s <path>] [--source <path>] \t Set source file path. Supported extensions are .xml or .csv\n" +
            "[-a <arg>] [--action <arg>] \t Set action for provided source file: [COUNT, SUM, AVG, LIST]\n" +
            "[-c <Int>] [--client <Int>] \t <optional> Filter requests by client id\n")
}