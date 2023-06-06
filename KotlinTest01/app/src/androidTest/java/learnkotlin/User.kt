package learnkotlin

data class User(val name: String?)

fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

fun main() {
    val user = User("leavesC")
    val name = user.name ?: fail("no name")
    println(name) //leavesC

    val user1 = User(null)
    val name1 = user1.name ?: fail("no name0001")
    println(name1.length) //IllegalStateException
}
