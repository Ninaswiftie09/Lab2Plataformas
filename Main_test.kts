
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    fun testPrecedence() {
        assertEquals(1, precedence('+'))
        assertEquals(1, precedence('-'))
        assertEquals(2, precedence('*'))
        assertEquals(2, precedence('/'))
        assertEquals(3, precedence('^'))
        assertEquals(4, precedence('r'))
        assertEquals(4, precedence('e'))
        assertEquals(0, precedence('x'))
    }

    @Test
    fun testEvaluate() {
        assertEquals(7.0, evaluate("(3 + 4)"))
        assertEquals(10.0, evaluate("5 * (2 + 3)"))
        assertEquals(27.0, evaluate("3 ^ 3"))
        assertEquals(16.0, evaluate("4 r 16"))  //
        assertEquals(20.085536923187668, evaluate("3 e"))

        @Test
        fun testApplyOp() {
            assertEquals(5.0, applyOp('+', 2.0, 3.0))
            assertEquals(-1.0, applyOp('-', 2.0, 3.0))
            assertEquals(6.0, applyOp('*', 2.0, 3.0))
            assertEquals(2.0, applyOp('/', 6.0, 3.0))
            assertEquals(8.0, applyOp('^', 2.0, 3.0))
            assertEquals(2.0, applyOp('r', 4.0, 16.0))
            assertEquals(20.085536923187668, applyOp('e', 3.0, 0.0))
        }
    }
