package section4

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val job1 = launch(start = CoroutineStart.LAZY) {
        println("지연 코루틴 실행1")
    }

    val job2 = launch {
        delay(1000) // 코루틴이 일시 중단 됨.
        println("코루틴 실행2")
    }

    val job3 = launch {
        println("코루틴 실행3")
    }

    println("1 초 대기")
    delay(1000)

    /**
     * - cancel 함수는 코루틴을 곧바로 추소하지 않고, 취소 확인용 플래그를 '취소 요청됨'으로 바꾸는 역할만 하고, 실질적인 코루틴 취소는 해당 플래그가 확인되는 시점에 이루어진다.
     */
    job1.cancel() // .cancel()을 호출함으로써, 코루틴의 실행을 취소할 수 있다.
    job2.cancelAndJoin() // 취소 요청 후, 취소가 완료된 후에야 다음 코드를 실행한다. '취소'는 코루틴이 일시 중단되는 시점에 수행된다.
    job3.start() // .start() 또는 .join()을 호출함으로써, 지연 코루틴을 실행할 수 있다.

}
