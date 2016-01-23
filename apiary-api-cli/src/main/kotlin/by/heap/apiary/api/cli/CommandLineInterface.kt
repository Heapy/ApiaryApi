package by.heap.apiary.api.cli

import com.beust.jcommander.Parameter

/**
 * Command line interface description.
 *
 * @author Ibragimov Ruslan
 */
class CommandLineInterface {
    val settings = Settings("application")

    @Parameter(names = arrayOf("-input"), description = "Input api blueprint file.")
    val input = settings.getString("input.file")

    @Parameter(names = arrayOf("-output"), description = "Output api blueprint preview file.")
    val output = settings.getString("output.file")

    @Parameter(names = arrayOf("-name"), description = "Name of Apiary project.")
    val name = settings.getString("project.name")

    @Parameter(names = arrayOf("-token"), description = "Apiary user's token.")
    val token = settings.getString("user.token")

    @Parameter(names = arrayOf("-op"), description = "Type of operation {publish, fetch, preview}.")
    val operation: String = "preview"

    @Parameter(names = arrayOf("--help", "-h"), help = true)
    val help: Boolean = false
}
