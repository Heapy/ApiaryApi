package by.heap.apiary.api.cli

import by.heap.apiary.api.lib.Fetch
import by.heap.apiary.api.lib.Preview
import by.heap.apiary.api.lib.Publish
import com.beust.jcommander.JCommander

fun main(args: Array<String>) {
    val params = CommandLineInterface();
    JCommander(params, *args);

    when (params.operation) {
        "publish" -> Publish.publish(params.name, params.token, params.input)
        "preview" -> Preview.preview(params.input, params.output)
        "fetch" -> Fetch.fetch(params.name, params.token, params.input)
    }
}
