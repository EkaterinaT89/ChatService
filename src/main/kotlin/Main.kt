fun main () {
    var message = Message(text = "Text", readStatus = true, userId = 12, messageId = 1)
    var message2 = Message(text = "Text2", readStatus = true, userId = 14, messageId = 2)
    var message3 = Message(text = "Text3", readStatus = false, userId = 12, messageId = 3)
    var message4 = Message(text = "Text4", readStatus = true, userId = 13, messageId = 4)
    var message5 = Message(text = "Text5", readStatus = true, userId = 12, messageId = 5)
    var message6 = Message(text = "Text6", readStatus = true, userId = 12, messageId = 6)

    var user = User(12)
    var user2 = User(14)
    var user4 = User(13)

    var chatServise = ChatService

    chatServise.addUsers(user)
    chatServise.addUsers(user2)
    chatServise.addUsers(user4)

    chatServise.add(user, message)
    chatServise.add(user2, message2)
    chatServise.add(user, message3)

    chatServise.getMessages(13)
    chatServise.getMessagesWithId(122, 2, 1)
    chatServise.getUnreadMessageCount(14)
    chatServise.changeMessage(12, 1, message4)
    chatServise.deleteMessage(14, 2)
    chatServise.deleteChat(14)
    chatServise.print()

}