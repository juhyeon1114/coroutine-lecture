package section4

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val job1 = launch {
        delay(1000)
    }
    println(job1) // Active(실행중)
    assert(job1.isActive)

    val job2 = launch(start = CoroutineStart.LAZY) {
        delay(1000)
    }
    println(job2) // New(생성)
    assert(!job2.isActive && !job2.isCompleted && !job2.isCancelled)
    job2.cancel()

    val job3 = launch() {
        delay(1000)
    }
    job3.join()
    println(job3) // Completed(실행 완료)
    assert(job3.isCompleted && !job3.isCancelled)

    val job4 = launch {
        delay(1000)
    }
    job4.cancel()
    println(job4) // Canceling(취소중)
    assert(job4.isCancelled && !job4.isCompleted)

    val job5 = launch {
        delay(1000)
    }
    job5.cancelAndJoin()
    println(job5) // Canceled(취소)
    assert(job5.isCancelled && job5.isCompleted)
}
