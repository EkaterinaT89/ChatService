import java.util.function.BiPredicate

object ChatService {

    private val chat: HashMap<Int, MutableList<Message>> = hashMapOf()

    fun addUsers(user: User): User {
        if (!chat.containsKey(user.userId)) {
            chat.put(user.userId, mutableListOf())
        }
        return user
    }

    fun add(user: User, message: Message): Message {
        if (chat.containsKey(user.userId)) {
            chat.get(user.userId)?.add(message)
        }
        return message
    }

    fun deleteChat(userIdForDelete: Int): Boolean {
        chat.forEach {
            if (chat.containsKey(userIdForDelete)) {
                chat.remove(userIdForDelete)
                return true
            }
        }
        throw UserNotFoundException ("Такого пользователя нет!")
    }

    fun changeMessage(UID: Int, messageIdForChange: Int, message2: Message): Message {
        chat.forEach {
            if (chat.containsKey(UID)) {
                val messagesFromChats = chat.getValue(UID)
                for (message in messagesFromChats) {
                    if (message.messageId == messageIdForChange) {
                        messagesFromChats.add(message2)
                        messagesFromChats.remove(message)
                        return message2
                    }
                }
            }
        }
        throw MessageNotFoundException("Такого сообщения нет")
    }

    fun deleteMessage(userId: Int, messageIdForDelete: Int): Boolean {
        chat.forEach {
            if (chat.containsKey(userId)) {
                val messagesFromChats = chat.getValue(userId)
                for (message in messagesFromChats) {
                    if (message.messageId == messageIdForDelete && messagesFromChats.isNotEmpty()) {
                        messagesFromChats.remove(message)
                        return true
                    }
                    if (messagesFromChats.isEmpty()) {
                        deleteChat(userId)
                        return true
                    } else throw MessageNotFoundException("Такого сообщения нет")
                }
            }
        }
       throw UserNotFoundException("Пользователя с таким ID нет.")
    }

    fun print() {
        chat.forEach { (key, value) ->
            if (value.isNotEmpty()) println("Чат с пользователем с ID $key: \n $value") else
                println("Чат с пользователем с ID $key: \n нет сообщений.")
        }
    }

    fun getUnreadMessageCount(userId: Int): Int {
        chat.forEach {
            if (chat.containsKey(userId)) {
                val messagesFromChats = chat.getValue(userId)
                val unreadMessageList = messagesFromChats.filter { message -> return@filter !message.readStatus }
                val size = unreadMessageList.size
                println("Непрочитанных сообщений $size ")
                return size
            }
        }
        throw UserNotFoundException("Пользователя с таким ID нет.")
    }

    fun getMessages(userId: Int) {
        if (chat.containsKey(userId)) {
            val messages = chat.getValue(userId)
            if (messages.isEmpty()) println("В чате с пользователем $userId сообщений нет!")
            else println("Чат с пользователем с ID $userId \n $messages")
        } else throw UserNotFoundException("Пользователя с таким ID нет.")
    }

    fun getMessagesWithId(userId: Int, messageId: Int, countOfMessages: Int) {
        if (chat.containsKey(userId)) {
            val messages =
                chat.getValue(userId).filter { message -> message.messageId > messageId }.take(countOfMessages)
            if (messages.isEmpty()) println("В чате с пользователем с ID $userId по указанным условиям сообщений нет!")
            else println("Чат с пользователем с ID $userId \n $messages")
        } else throw UserNotFoundException("Пользователя с таким ID нет.")
    }

}


