package learnkotlin


fun main() {
    val count = "fjdasklfjkl".lettersCount()
    println("---" + count + "---")
}

fun String.lettersCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}