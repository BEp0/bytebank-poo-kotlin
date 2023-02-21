import org.junit.jupiter.api.Test

class SimpleTest {

    @Test
    fun `array test loop`() {
        val fruitList = listOf("pineapple", "apple", "strawberry")
        fruitList.forEach { println(it) }

        val sortedList = fruitList.sorted()
        sortedList.forEach { println(it) }
    }
}