package me.henriquelluiz.queue

@Suppress("UNCHECKED_CAST")
class ArrayQueue<T> (private val capacity: Int, private val strategyFunc: (value: Int, capacity: Int?) -> Int) {
    private var elements: Array<Any?> = arrayOfNulls(capacity)
    private var front = 0
    private var rear = -1
    private var size = 0

    fun enqueue(element: T) {
        if (size == capacity) { throw IllegalStateException("Queue is full.") }
        else {
            rear = strategyFunc(rear, capacity)
            elements[rear] = element
        }
        size++
    }

    fun dequeue(): T? {
        if (elements.isEmpty()) { throw IllegalStateException("Queue is empty.") }
        else {
            val frontElement = elements[front]
            front = strategyFunc(front, capacity)
            size--
            return frontElement as T
        }
    }

    fun peek(): T? { return if (elements.isNotEmpty()) elements[front] as T else null }
    override fun toString(): String {
        val result = StringBuilder("Queue(")
        for (i in 0 until size) {
            result.append(elements[(front + i) % capacity])
            if (i < size - 1) { result.append(", ") }
        }
        result.append(")")
        return result.toString()
    }
}

fun main() {
    val simple = ArrayQueue<String>(5) { value, _ -> value + 1 }
    simple.enqueue("A")
    simple.enqueue("B")
    simple.enqueue("C")
    simple.enqueue("D")
    simple.enqueue("E")
    println(simple)
    println(simple.peek())
    simple.dequeue()
    println(simple)
//    simple.enqueue("") -> THROW INDEX OUT OF BOUNDS

    println()
    println("__CIRCULAR QUEUE__")

    val circular = ArrayQueue<String>(5) { value, capacity -> (value + 1) % capacity!! }
    circular.enqueue("A")
    circular.enqueue("B")
    circular.enqueue("C")
    circular.enqueue("D")
    circular.enqueue("E")
    println(circular)
    println(circular.peek())
    circular.dequeue()
    circular.enqueue("DON'T THROW 'INDEX OUT OF BOUNDS'")
    println(circular)
}