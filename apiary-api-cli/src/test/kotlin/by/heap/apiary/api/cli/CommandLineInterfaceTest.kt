package by.heap.apiary.api.cli

import com.beust.jcommander.JCommander
import org.junit.Assert.assertEquals
import org.junit.Test

class CommandLineInterfaceTest {
    @Test
    fun test_operation() {
        val array = arrayOf("-op", "publish")
        val params = getParams(array)

        assertEquals(params.operation, "publish")
    }

    @Test
    fun test_all_arguments() {
        val array = arrayOf("-op", "publish",
                "-input", "\"few words\"",
                "-output", "/opt/file/",
                "-name", "test",
                "-token", "42")
        val params = getParams(array)

        assertEquals(params.operation, "publish")
        assertEquals(params.input, "few words")
        assertEquals(params.output, "/opt/file/")
        assertEquals(params.name, "test")
        assertEquals(params.token, "42")
    }
}

fun getParams(array: Array<String>): CommandLineInterface {
    val params = CommandLineInterface()
    JCommander(params, *array)

    return params
}
