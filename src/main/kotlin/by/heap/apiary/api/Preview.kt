package by.heap.apiary.api

import org.apache.http.client.fluent.Request
import org.apache.http.entity.ContentType
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

internal object Preview {

    fun preview(inputFileName: String, outputFilename: String): Boolean {

        // Because Apiary doesn't accept files with CRLF line endings. Should use LF insted.
        val fileContent = File(inputFileName).readText(Charset.defaultCharset()).replace("\r\n", "\n")

        val request = Request.Post("https://api.apiary.io//blueprint/generate")
                .setHeader("Accept-Encoding", "gzip, deflate")
                .setHeader("Accept", "text/html")
                .bodyString(fileContent, ContentType.TEXT_PLAIN)

                .execute()

        val response = request.handleResponse {
            it.entity.toResponse()
        }

        val error = response.error ?: false

        if (error) {
            println("An error occurred.")
            println(response.message)
        } else {
            val output = Paths.get(outputFilename)
            if (Files.exists(output)) Files.delete(output)

            if (response.code != null) {
                Files.write(output, response.code.toByteArray())
            }

            println("Preview generation completed. File '${output.toAbsolutePath()}' generated.")
            response.message?.let {
                println(response.message)
            }
        }

        return error
    }
}
