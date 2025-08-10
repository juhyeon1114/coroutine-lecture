package section5

import kotlinx.coroutines.*

private val myDispatcher1 = newSingleThreadContext("MyThread-1")
private val myDispatcher2 = newSingleThreadContext("MyThread-2")

fun main() = runBlocking<Unit> {
    println("[${Thread.currentThread().name}] 코루틴 실행 1")
    withContext(myDispatcher1) {
        println("[${Thread.currentThread().name}] 코루틴 실행 2")
        withContext(myDispatcher2) {
            println("[${Thread.currentThread().name}] 코루틴 실행 3")
        }
        println("[${Thread.currentThread().name}] 코루틴 실행 4")
    }
    println("[${Thread.currentThread().name}] 코루틴 실행 5")
}
