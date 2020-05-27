package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val firstName = fullName?.split(" ")?.getOrNull(0)
        val lastName = fullName?.split(" ")?.getOrNull(1)

//        return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val builder = StringBuilder()
        for (ch in payload) {
            builder.append(when (ch){
                ' ' -> divider
                'А' -> "А"
                'а' -> "a"
                'Б' -> "B"
                'б' -> "b"
                'В' -> "V"
                'в' -> "v"
                'Г' -> "G"
                'g' -> "g"
                'Д' -> "D"
                'д' -> "D"
                'Е' -> "E"
                'е' -> "e"
                'Ё' -> "Yo"
                'ё' -> "yo"
                'Ж' -> "J"
                'ж' -> "j"
                'З' -> "Z"
                'з' -> "z"
                'И' -> "I"
                'и' -> "i"
                'Й' -> "Y"
                'й' -> "й"
                'К' -> "K"
                'к' -> "k"
                'Л' -> "L"
                'л' -> "l"
                'М' -> "M"
                'м' -> "m"
                'Н' -> "N"
                'н' -> "n"
                'О' -> "О"
                'о' -> "o"
                'П' -> "P"
                'п' -> "p"
                'Р' -> "R"
                'р' -> "r"
                'С' -> "S"
                'с' -> "c"
                'Т' -> "T"
                'т' -> "t"
                'У' -> "U"
                'у' -> "u"
                'Ф' -> "F"
                'ф' -> "f"
                'Х' -> "H"
                'х' -> "h"
                'Ц' -> "TS"
                'ц' -> "ts"
                'Ч' -> "Ch"
                'ч' -> "ch"
                'Ш' -> "Sh"
                'ш' -> "sh"
                'Щ' -> "Sch"
                'щ' -> "sch"
                'Ь' -> "\'"
                'ь' -> "\'"
                'Ъ' -> "\""
                'ъ' -> "\""
                'Э' -> "E"
                'э' -> "e"
                'Ю' -> "Yu"
                'ю' -> "yu"
                'Я' -> "Ya"
                'я' -> "ya"
                else -> ch
            })
        }
        return builder.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val first: Char? = if (firstName?.length != 0) firstName?.get(0) else null
        val last: Char? = if (lastName?.length != 0) lastName?.get(0) else null
        return when {
            first == null && last == null -> null
            first == null && last != null -> if (last.isLetter()) last.toUpperCase() else null
            first != null && last == null -> if (first.isLetter()) first.toUpperCase() else null
            first != null && last != null -> when {
                first.isLetter() && last.isLetter() ->
                    first.toUpperCase().toString() + last.toUpperCase().toString()
                first.isLetter() && !last.isLetter() ->
                    first.toUpperCase()
                !first.isLetter() && last.isLetter() ->
                    last.toUpperCase()
                else -> null
            }
            else -> null
        }.toString()
    }
}
