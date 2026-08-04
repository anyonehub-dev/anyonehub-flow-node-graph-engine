// Copyright 2024 anyone-Hub

#!/usr/bin/env kotlin

import java.io.File

val rootDir = File(".")

val allowedExtensions = setOf("kt", "kts", "java")
val newCopyright = "// Copyright 2024 anyone-Hub"

// Regex to aggressively match any line containing 'copyright' (case insensitive),
// including leading/trailing whitespace, block comments if formatted line-by-line,
// or consecutive lines of old headers. We specifically look at the top of the file.
val copyrightRegex = Regex("(?i)^\\s*//.*copyright.*\\s*")

fun processFile(file: File) {
    if (!file.isFile || !file.canRead() || !file.canWrite()) return
    
    val extension = file.extension
    if (extension !in allowedExtensions) return

    val lines = file.readLines()
    if (lines.isEmpty()) return

    // Find where the actual code starts by skipping old copyright headers
    // and empty lines at the very top of the file.
    var firstCodeLineIndex = 0
    for (i in lines.indices) {
        val line = lines[i]
        if (line.matches(copyrightRegex) || line.isBlank()) {
            firstCodeLineIndex++
        } else {
            break
        }
    }

    val actualCode = lines.subList(firstCodeLineIndex, lines.size).joinToString("\n")
    val newContent = "$newCopyright\n\n$actualCode\n"

    // Only write if there's a meaningful change (avoids rewriting perfectly fine files twice)
    val currentContent = file.readText()
    if (currentContent != newContent) {
        file.writeText(newContent)
        println("Updated: ${file.path}")
    }
}

println("Starting Copyright Takeover...")

rootDir.walkTopDown()
    .onEnter { dir ->
        // Prevent walking into `.git` to protect submodule integrity,
        // and ignore `build` directories to speed up the process.
        val name = dir.name
        name != ".git" && name != "build" && name != ".gradle" && name != ".github_modules"
    }
    .forEach { file ->
        processFile(file)
    }

println("Copyright Takeover Complete.")
