package by.heap.apiary.api.lib

import org.apache.http.client.fluent.Request
import org.apache.http.message.BasicNameValuePair
import java.nio.charset.Charset

object Publish {

    fun publish(projectName: String, userToken: String, file: String): ApiaryResponse {
        val request = Request.Post("https://api.apiary.io//blueprint/publish/$projectName")
                .setHeader("Authentication", "Token $userToken")
                .setHeader("Accept-Encoding", "gzip, deflate")
                .setHeader("Accept", "text/html")
                .bodyForm(
                        arrayListOf(
                                BasicNameValuePair("messageToSave", "Saving blueprint from apiary-client"),
                                BasicNameValuePair("code", file)
                        ),
                        Charset.defaultCharset()
                )

                .execute()

        return request.handleResponse {
            it.entity.toResponse()
        }
    }
}
