package section4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

/**
 * 코루틴이 취소를 확인하는 시점
 * - 코루틴이 일시 중단되는 시점
 * - 코루틴이 실행을 대기하는 시점
 *
 * 코루틴에 취소 확인 시점을 만드는 세가지 방법
 * - delay 함수를 사용해서 취소 확인 시점 만들기 → 불필요하게 작업을 지연시키는 단점이 있음.
 * - yield 함수를 사용해 취수 확인 시점 만들기 → yield는 코루틴이 사용중인 스레드를 양보(일시 중단)하고, 곧바로 재개 요청을한다.
 * - CoroutineScope.isActive를 사용한 취소 확인 → isActive
 *
 * - delay, yield는 코루틴을 일시중단하고, 재개 하는 과정을 거친다. 즉, CoroutineDispatcher에 의해 코루틴이 다시 스레드에 보내지는 과정을 거치기 때문에 비효율적이다.
 */
fun main() = runBlocking<Unit> {
    val job1 = launch(Dispatchers.Default) {
        while (true) {
            println("작업중1")
//            delay(1) // 취소 확인 시점 1
            yield() // 취소 확인 시점 2
        }
    }
    delay(2)
    job1.cancel()

    val job2 = launch(Dispatchers.Default) {
        println(this.isActive)
        while (this.isActive) {
            println("작업중2")
        }
        println(this.isActive)
    }
    delay(2)
    job2.cancel()
}
