import java.util.Stack

fun precedence(op: Char): Int {
    return when (op) {
        '+', '-' -> 1
        '*', '/' -> 2
        '^' -> 3
        else -> 0
    }
}

fun evaluate(expression: String): Double {
    val values = Stack<Double>()
    val ops = Stack<Char>()

    var i = 0
    while (i < expression.length) {
        val c = expression[i]

        when {
            c.isWhitespace() -> {
                i++
                continue
            }

            c.isDigit() -> {
                var buffer = StringBuilder()
                while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) {
                    buffer.append(expression[i++])
                }
                values.push(buffer.toString().toDouble())
                i--
            }

            c == '(' -> ops.push(c)

            c == ')' -> {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                }
                ops.pop()
            }

            c == '+' || c == '-' || c == '*' || c == '/' || c == '^' -> {
                while (ops.isNotEmpty() && precedence(ops.peek()) >= precedence(c)) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                }
                ops.push(c)
            }
        }
        i++
    }

    while (ops.isNotEmpty()) {
        values.push(applyOp(ops.pop(), values.pop(), values.pop()))
    }

    return values.pop()
}

fun applyOp(op: Char, b: Double, a: Double): Double {
    return when (op) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> a / b
        '^' -> Math.pow(a, b)
        else -> throw UnsupportedOperationException("Sintaxis incorrecta")
    }
}



fun main() {
    println("Ingrese la operación que desea realizar: ")
    val input = readln()

    try {
        val result = evaluate(input)
        println("Resultado: $result")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
