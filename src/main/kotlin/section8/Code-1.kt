package section8

import kotlinx.coroutines.*

/**
 * Supervisor Job을 사용한 예외 전파 제한
 * - Supervisor Job 객체는 자식 코루틴으로부터 예외를 전파 받지 않는 특수한 Job객체이다.
 * → 예외를 전파 받지 않아서 자식 코루틴에서 예외가 발생하더라도 취소되지 않는다.
 * - Supervisor Job 객체는 자식 코루틴에서 발생한 예외가 다른 자식 코루틴에게 영향을 미치지 못하게 만드는데 사용된다.
 */
fun main() = runBlocking<Unit> {
	// runBlocking과의 구조화를 깨지 않기 위한 방식으로 Supervisor job 생성
	val supervisorJob = SupervisorJob(parent = coroutineContext[Job])

	launch(CoroutineName("Coroutine1") + supervisorJob) {
		launch(CoroutineName("Coroutine3")) {
			throw Exception("예외 발생")
		}
		delay(100)
		println("[${Thread.currentThread().name}] 코루틴 실행 1")
	}

	launch(CoroutineName("Coroutine2") + supervisorJob) {
		delay(100)
		println("[${Thread.currentThread().name}] 코루틴 실행 2")
	}
	delay(1000)

	supervisorJob.complete()
}
