package section7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

/**
 * 부모 코루틴의 실행 환경 상속
 * - 자식 코루틴은 부모 코루틴의 실행 환경을 상속한다.
 * - 단, Job은 상속되지 않고, 코루틴 빌더 함수(launch, async)는 호출될때마다 Job을 새롭게 생성한다.
 */
fun main() = runBlocking<Unit> {
	// 컨텍스트 상속
	val context = newSingleThreadContext("MyThread") + CoroutineName("CoroutineA")
	launch(context) {
		println("[${Thread.currentThread().name}] ${coroutineContext[CoroutineName]}")
		launch {
			println("[${Thread.currentThread().name}] ${coroutineContext[CoroutineName]}")
		}
	}

	// 컨텍스트 덮어쓰기
	val context2 = newSingleThreadContext("MyThread") + CoroutineName("CoroutineA")
	launch(context2) {
		println("[${Thread.currentThread().name}] ${coroutineContext[CoroutineName]}")
		launch(CoroutineName("CoroutineB")) {
			println("[${Thread.currentThread().name}] ${coroutineContext[CoroutineName]}")
		}
	}
}
