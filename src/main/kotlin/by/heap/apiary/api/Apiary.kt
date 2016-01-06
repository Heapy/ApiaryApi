package by.heap.apiary.api


fun main(args: Array<String>) {
    fun printHelp() {
        println("""
            Help Here!
        """.trimIndent())
    }

    val settings = Settings("application")
    val inputFileName = settings.getString("input.file")
    val outputFileName = settings.getString("output.file")
    val projectName = settings.getString("project.name")
    val userToken = settings.getString("user.token")

    if (args.size == 0) {
        printHelp()
    } else {
        try {
            when (args[0]) {
                "publish" -> Publish.publish(projectName, userToken, inputFileName)
                "preview" -> Preview.preview(inputFileName, outputFileName)
                "fetch" -> Fetch.fetch(projectName, userToken, inputFileName)
                else -> printHelp()
            }
        } catch (e: Exception) {
            println("An exception occurred. Exit...")
            e.printStackTrace()
            System.exit(1)
        }

    }
}