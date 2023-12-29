package me.henriquelluiz.stack

class ArrayStack<T> {
    private var elements: MutableList<T> = mutableListOf()

    fun push(element: T) {
        elements.add(element)
    }

    fun pop(): T? {
        if (elements.isEmpty()) {
            println("The stack is empty. Cannot push.")
            return null
        }
        val elementOfTop: T = elements.last()
        elements.removeAt(elements.size - 1)
        return elementOfTop
    }

    fun peek(): T? { return elements.lastOrNull() }
    override fun toString(): String {
        return elements.reversed().joinToString(",", "Stack(", ")")
    }
}

fun main() {
    val arrayStack = ArrayStack<Int>()
    arrayStack.push(5)
    arrayStack.push(7)
    arrayStack.push(16)
    arrayStack.push(21)
    println(arrayStack)
    println(arrayStack.pop())
    println(arrayStack)
    println(arrayStack.peek())
}