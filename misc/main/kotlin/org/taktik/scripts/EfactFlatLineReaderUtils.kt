package org.taktik.scripts

import java.io.File

/**
 * Read a list of flat billing files and write one "YML like syntax" output file with parsed data for each.
 *
 * @param args list of input filenames
 */
fun main(args: Array<String>) {
    args.forEach { fn ->
        val f = File(fn)
        val reader = f.bufferedReader()

        reader.use { r ->
            File(f.parent, f.nameWithoutExtension + ".yml").bufferedWriter().use { w ->
                w.write(
                    BelgianInsuranceTestInvoicingFormatReader("fr")
                        .parse(r, false)
                        .joinToString("\n") { it.toString() })
            }
        }
    }
}
