package by.heap.apiary.api.cli

import java.util.Arrays
import java.util.Properties

class Settings(val settings: String) {

    val resources = Properties()

    init {
        Arrays.asList("/$settings.properties", "/$settings-custom.properties").forEach {
            val url = resources.javaClass.getResource(it)
            if (url === null) return@forEach
            resources.javaClass.getResourceAsStream(it).use {
                resources.load(it)
            }
        }
    }

    fun printSettings() {
        resources.stringPropertyNames().forEach {
            println("Property: '${it}' has value: '${resources.get(it)}'")
        }
    }

    fun getString(key: String): String {
        return resources.get(key)!! as String
    }

    fun getInt(key: String): Int {
        return (resources.get(key)!! as String).toInt()
    }
}