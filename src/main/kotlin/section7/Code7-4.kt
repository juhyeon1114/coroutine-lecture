package section7

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
	// runBlocking의 Scope에서 벗어나는 새로운 Scope → 구조화가 깨짐
	val newScope = CoroutineScope(Dispatchers.IO)

	newScope.launch(CoroutineName("Coroutine1")) {
		launch(CoroutineName("Coroutine2")) {
			delay(100)
			println("[${Thread.currentThread().name}] 코루틴 실행 2")
		}
		launch(CoroutineName("Coroutine3")) {
			delay(100)
			println("[${Thread.currentThread().name}] 코루틴 실행 3")
		}
	}

	newScope.launch(CoroutineName("Coroutine4")) {
		launch(CoroutineName("Coroutine5")) {
			delay(100)
			println("[${Thread.currentThread().name}] 코루틴 실행 5")
		}
	}

	// ---------

	// runBlocking의 Scope에서 벗어나는 새로운 Root Job → 구조화가 깨짐
	val newRootJob = Job()

	launch(CoroutineName("Coroutine11") + newRootJob) {
		launch(CoroutineName("Coroutine22")) {
			delay(100)
			println("[${Thread.currentThread().name}] 코루틴 실행 22")
		}
		launch(CoroutineName("Coroutine33")) {
			delay(100)
			println("[${Thread.currentThread().name}] 코루틴 실행 33")
		}
	}

	launch(CoroutineName("Coroutine44") + newRootJob) {
		launch(CoroutineName("Coroutine55")) {
			delay(100)
			println("[${Thread.currentThread().name}] 코루틴 실행 55")
		}
	}

}
