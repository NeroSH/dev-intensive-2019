package ru.skillbranch.devintensive.extensions

fun String.stripHtml() = this
    .replace("<[^>]*>".toRegex(), "")
    .replace("\\s{2,}".toRegex(), " ")

fun String.truncate(last: Int = 16) = when (this?.get(last + 1)) {
    ' ' -> this.substring(0, last)
    else -> this.substring(0, last) + "..."
}