package section8

import kotlinx.coroutines.*

/**
 * CancellationException은 부모 코루틴으로 전파되지 않는다.
 */
fun main() = runBlocking<Unit> {
	launch(CoroutineName("Coroutine1")) {
		launch(CoroutineName("Coroutine2")) {
			throw CancellationException()
		}
		delay(100)
		println("Coroutine1")
	}
	delay(100)
	println("runBlocking")
}
