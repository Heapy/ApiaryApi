package by.heap.apiary.api.cli

import by.heap.apiary.api.lib.Fetch
import by.heap.apiary.api.lib.Preview
import by.heap.apiary.api.lib.Publish
import com.beust.jcommander.JCommander
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val params = CommandLineInterface();
    val jcommander = JCommander(params, *args);

    if (params.help) {
        jcommander.usage()
    }

    when (params.operation) {
        "publish" -> {
            println("Publishing project '${params.name}' from file '${params.input}'")
            println(Publish.publish(projectName = params.name, userToken = params.token, file = getFile(params.input)))
        }
        "preview" -> {
            println("Generating preview of '${params.input}' to file '${params.output}'")
            val out = Preview.preview(name = params.name, file = getFile(params.input))
            saveFile(fileName = params.output, fileContent = out)
        }
        "fetch" -> {
            println("Fetching project '${params.name}' to file '${params.input}.fetched'")
            val out = Fetch.fetch(params.name, params.token)

            out.code?.let {
                saveFile(fileName = "${params.input}.fetched", fileContent = it)
            }
        }
    }
}

private fun getFile(name: String) = File(name).readText(Charset.defaultCharset())

private fun saveFile(fileName: String, fileContent: String) {
    val path = Paths.get(fileName)
    if (Files.exists(path)) Files.delete(path)

    Files.createFile(path)
    Files.write(path, fileContent.toByteArray());
}
