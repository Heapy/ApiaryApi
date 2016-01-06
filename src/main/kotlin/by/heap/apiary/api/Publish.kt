package by.heap.apiary.api

import org.apache.http.client.fluent.Request
import org.apache.http.message.BasicNameValuePair
import java.io.File
import java.nio.charset.Charset

object Publish {

    fun publish(projectName: String, userToken: String, fileName: String): Boolean {
        val fileContent = File(fileName).readText(Charset.defaultCharset())

        val request = Request.Post("https://api.apiary.io//blueprint/publish/$projectName")
                .setHeader("Authentication", "Token $userToken")
                .setHeader("Accept-Encoding", "gzip, deflate")
                .setHeader("Accept", "text/html")
                .bodyForm(
                        arrayListOf(
                                BasicNameValuePair("messageToSave", "Saving blueprint from apiary-client"),
                                BasicNameValuePair("code", fileContent)
                        ),
                        Charset.defaultCharset()
                )

                .execute()

        val response = request.handleResponse {
            it.entity.toResponse()
        }

        val error = response.error ?: false

        if (error) {
            println("An error occurred.")
            println(response.message)
        } else {
            println("Successfully published.")
            response.message?.let {
                println(response.message)
            }
        }

        return error
    }
}
