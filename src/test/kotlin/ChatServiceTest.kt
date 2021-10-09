import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    val chatService = ChatService

    @Test
    fun deleteChatTrue() {
        val user = User(12)
        val message = Message(1, " ", true, 12)
        chatService.addUsers(user)
        chatService.add(user, message)
        val result = chatService.deleteChat(12)
        assertTrue(result)
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThrowDeleteChat() {
        val user = User(12)
        val message = Message(1, " ", true, 12)
        chatService.deleteChat(15)
    }

    @Test
    fun changeMessage() {
        val user = User(12)
        val message = Message(1, " ", true, 12)
        chatService.addUsers(user)
        chatService.add(user, message)
        val messageForChange = Message(2, " ", false, 12)
        val result = chatService.changeMessage(12, 1, messageForChange)
        assertEquals(messageForChange, result)
    }

    @Test(expected = MessageNotFoundException::class)
    fun shouldThrowChangeMessage() {
        val message = Message(1, " ", true, 12)
        chatService.changeMessage(1, 1, message)
    }

    @Test
    fun deleteMessage() {
        val user = User(12)
        val message = Message(1, " ", true, 12)
        chatService.addUsers(user)
        chatService.add(user, message)
        val result = chatService.deleteMessage(12, 1)
        assertTrue(result)
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThrowDeleteMessage() {
        val user = User(12)
        val message = Message(1, " ", true, 12)
        chatService.deleteMessage(12, 1)
    }

    @Test
    fun getUnreadMessageCount() {
        val user = User(12)
        val message = Message(1, " ", false, 12)
        chatService.addUsers(user)
        chatService.add(user, message)
        val countExpected = 1
        val result = chatService.getUnreadMessageCount(12)
        assertEquals(countExpected, result)
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThowCount() {
        val user = User(18)
        chatService.getUnreadMessageCount(18)
    }

}