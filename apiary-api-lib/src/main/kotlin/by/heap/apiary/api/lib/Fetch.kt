package by.heap.apiary.api.lib

import org.apache.http.client.fluent.Request

object Fetch {

    fun fetch(projectName: String, userToken: String): ApiaryResponse {
        val request = Request.Get("https://api.apiary.io//blueprint/get/$projectName")
                .setHeader("Authentication", "Token $userToken")
                .setHeader("Accept-Encoding", "gzip, deflate")
                .setHeader("Accept", "text/html")

                .execute()

        return request.handleResponse {
            it.entity.toResponse()
        }
    }
}

