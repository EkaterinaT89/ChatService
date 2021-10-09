class Message(
    var messageId: Int,
    val text: String,
    val readStatus: Boolean,
    var userId: Int,
    var type: Boolean? = null

    ) {

    fun setReadStatus(readStatus: Boolean): String {
        when (readStatus) {
            true -> return "\n Прочитанное сообщение."
            false -> return "Не прочитанное сообщение"
        }
    }

    fun chooseType(type: Boolean): String {
        when (type) {
            true -> return "Входящее сообщение"
            false -> return "Исходящее сообщение"
        }
    }

    override fun toString(): String {
        return " " + setReadStatus(readStatus) + "\n " +
                type?.let { chooseType(it)} + "\n " + "ID сообщения - $messageId, $text "
    }

}