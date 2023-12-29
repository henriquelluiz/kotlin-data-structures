package me.henriquelluiz.stack

class NodeStack<T> {
    private class Node<T> (val element: T, var next: Node<T>? = null)

    private var elements: Node<T>? = null
    private var size: Int = 0

    fun push(element: T) {
        val newNode = Node(element)
        if (size == 0) { elements = newNode }
        else {
            val temp = elements
            elements = newNode
            elements!!.next = temp
        }
        size += 1
    }

    fun pop(): T? {
        if (size == 0) { return null }
        else {
            val elementOfTop = elements!!.element
            elements = elements!!.next
            size -= 1
            return elementOfTop
        }
    }

    fun peek(): T? { return if (size == 0) null else elements!!.element  }

    override fun toString(): String {
        var elementsCopy = elements
        val result = StringBuilder("Stack(")
        while (elementsCopy != null) {
            result.append(elementsCopy.element)
            if (elementsCopy.next != null) { result.append(", ") }
            elementsCopy = elementsCopy.next
        }
        result.append(")")
        return result.toString()
    }
}

fun main() {
    val stack: NodeStack<String> = NodeStack()
    stack.push("A")
    stack.push("B")
    stack.push("C")
    stack.push("D")
    stack.push("E")
    println(stack)
    println(stack.pop())
    println(stack.peek())
    println(stack)
}