package me.henriquelluiz.queue

class NodeQueue<T> {
    private class Node<T>(val element: T, var next: Node<T>? = null)
    private var front: Node<T>? = null
    private var rear: Node<T>? = null
    private var size = 0

    fun enqueue(element: T) {
        val newNode: Node<T> = Node(element)
        if (size == 0) { front = newNode }
        else { rear!!.next = newNode }
        rear = newNode
        size++
    }

    fun dequeue(): T? {
        if (size == 0) { throw IllegalStateException("Queue is empty. Cannot dequeue.") }
        else {
            val element = front!!.element
            front = front!!.next
            size--
            if (size == 0) { rear = null }
            return element
        }
    }

    fun getFront(): T? { return if (size != 0) front!!.element else null }
    fun getRear(): T? { return if (size != 0) rear!!.element else null }

    fun peek(index: Int): T? {
        if (size > 0 && index <= size && index > 0) {
            var frontCopy = front
            for (i in 1 until index) {
                frontCopy = frontCopy!!.next
            }
            return frontCopy!!.element
        }
        println("Don't use '0' as index!")
        return null
    }

    override fun toString(): String {
        val result = StringBuilder("Queue(")
        var frontCopy = front
        for (i in 0 until size) {
            result.append(frontCopy!!.element)
            if (frontCopy.next != null) {
                result.append(", ")
            }
            frontCopy = frontCopy.next
        }
        result.append(")")
        return result.toString()
    }
}

fun main() {
    val queue = NodeQueue<Int>()
    queue.enqueue(4)
    queue.enqueue(7)
    queue.enqueue(13)
    queue.enqueue(17)
    println(queue)
    queue.dequeue()
    println(queue)
    println(queue.getFront())
    println(queue.getRear())
    println(queue.peek(0))
}