package by.heap.apiary.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.http.HttpEntity
import org.apache.http.util.EntityUtils

data class ApiaryResponse (val error: Boolean?, val message: String?, val code: String?)

val mapper = ObjectMapper().registerKotlinModule()

fun HttpEntity.toResponse(): ApiaryResponse = mapper.readValue(EntityUtils.toString(this))
