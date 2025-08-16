package section8

import kotlinx.coroutines.*

/**
 * Supervisor job 객체는 CoroutineScope 생성 함수와 자주 사용한다.
 */
fun main() = runBlocking<Unit> {
	val coroutineScope = CoroutineScope(SupervisorJob())

	coroutineScope.apply {
		launch(CoroutineName("Coroutine1")) {
			launch(CoroutineName("Coroutine3")) {
				throw Exception("예외 발생")
			}
			delay(100)
			println("[${Thread.currentThread().name}] 코루틴 실행 1")
		}

		launch(CoroutineName("Coroutine2")) {
			delay(100)
			println("[${Thread.currentThread().name}] 코루틴 실행 2")
		}
		delay(1000)
	}
}
