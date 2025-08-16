package section10

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * join과 await의 양보 자세히 알아보기
 * - Job의 join 함수나 Deferred의 await 함수가 호출되면 해당 함수를 호출한 코루틴은 스레드를 양보하고, join 또는 await의 대상이 된 코루틴이 완료될 때까지 일시 중단된다.
 * - 하나의 코루틴이 스레드를 양보하지 않으면 다른 코루틴은 스레드를 점유하지 못한다.
 */
fun main() = runBlocking<Unit> {
	val job = launch {
		println("1. Job1 시작")
		delay(1000)
		println("2. Job1 종료")
	}
	println("3. runBlocking 코루틴이 일시중단되고 메인 스레드가 양보됨")
	job.join()
	println("4. runBlocking이 메인 스레드에 보내져 작업이 다시 재개됨")
}
