package com.example.introtocompose

fun main() {
    val icons = "itemTesteTomposeString" //ok!
    val illustra = "item_teste_compose_string" //ok!

    println(illustra.snakeToLowerCamelCase())
    println(illustra.snakeToUpperCamelCase())
    println(icons.camelToSnakeCase())
}

val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
val snakeRegex = "_[a-zA-Z]".toRegex()

// String extensions
fun String.camelToSnakeCase(): String {
    return camelRegex.replace(this) {
        "_${it.value}"
    }.lowercase()
}

fun String.snakeToLowerCamelCase(): String {
    return snakeRegex.replace(this) {
        it.value.replace("_","")
            .uppercase()
    }
}

fun String.snakeToUpperCamelCase(): String {
    return this.snakeToLowerCamelCase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}