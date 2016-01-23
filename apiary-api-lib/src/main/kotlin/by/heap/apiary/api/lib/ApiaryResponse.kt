package by.heap.apiary.api.lib

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.http.HttpEntity
import org.apache.http.util.EntityUtils

data class ApiaryResponse (val error: Boolean?, val message: String?, val code: String?)

val mapper = jacksonObjectMapper()

fun HttpEntity.toResponse(): ApiaryResponse = mapper.readValue(EntityUtils.toString(this))
