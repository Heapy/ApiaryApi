package by.heap.apiary.api.lib

object Preview {

    private fun getFile(name: String, file: String): String {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <title>${name}</title>
        </head>
        <body>
          <script src="https://api.apiary.io/seeds/embed.js"></script>
          <script>
            var embed = new Apiary.Embed({
              apiBlueprint: "${escape(file)}"
            });
          </script>
        </body>
        </html>
        """.trimIndent()
    }

    private fun escape(file: String): String {
        val mappings = mapOf(
                "\\" to "\\\\",
                "\r\n" to "\n",
                "\r" to "\n",
                "\"" to "\\\"",
                "'" to "\\'"
        )

        var out = file;

        mappings.forEach { out = out.replace(it.key, it.value) }

        return out
    }

    fun preview(name: String, file: String): String {
        return getFile(name, file)
    }
}
