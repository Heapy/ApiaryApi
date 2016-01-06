package by.heap.apiary.api

import org.apache.http.client.fluent.Request
import java.nio.file.Files
import java.nio.file.Paths

internal object Fetch {

    fun fetch(projectName: String, userToken: String, inputFile: String): Boolean {
        val request = Request.Get("https://api.apiary.io//blueprint/get/$projectName")
                .setHeader("Authentication", "Token $userToken")
                .setHeader("Accept-Encoding", "gzip, deflate")
                .setHeader("Accept", "text/html")

                .execute()

        val response = request.handleResponse {
            it.entity.toResponse()
        }

        val error = response.error ?: false

        if (error) {
            println("An error occurred.")
            println(response.message)
        } else {
            val path = Paths.get("${inputFile}.fetched")
            if (Files.exists(path)) Files.delete(path)
            response.code?.let {
                Files.createFile(path)
                Files.write(path, response.code.toByteArray());
            }

            println("Successfully fetched.")
            response.message?.let {
                println(response.message)
            }
        }

        return error
    }
}

