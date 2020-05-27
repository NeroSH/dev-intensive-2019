package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(
    id : String,
    from : User?,
    char : Chat,
    isIncoming : Boolean = false,
    date: Date = Date(),
    var image: String?
) : BaseMessage(id, from, char, isIncoming, date)  {
    override fun formatMessage(): String = "id:$id ${from?.firstName} " +
            "${if(isIncoming) "получил" else "отправил"} изображение $image ${date.humanizeDiff()}"
}