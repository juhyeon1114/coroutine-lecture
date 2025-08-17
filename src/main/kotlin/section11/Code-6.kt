package section11

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.thread
import kotlin.coroutines.resume

/**
 * Continuation Passing Style
 * - Continuation passing style? 어떤 대상에게 이어서 수행할 작업을 함께 전달하는 방식
 *
 * - 코루틴은 코드를 실행하는 도중에 일시 중단하고 필요한 시점에 다시 재개하는 기능을 지원한다.
 * - 코루틴은 일시 중단 시점에 남은 작업 정보가 Continuation 객체에 저장된다.
 * - Continuation의 resumeWith 함수가 호출되면 저장된 작업 정보가 복원돼 남은 작업들이 마저 실행된다.
 *
 * - suspendCancellableCoroutine 함수를 사용하면 코루틴이 일시 중단되고,
 *   함수 람다식의 수신객체인 CancellableContinuation에 resume 함수가 호출된다.
 */
fun main() = runBlocking {
	// Continuation을 직접 사용하는 예시
	val result = suspendCancellableCoroutine { continuation ->
		thread {
			Thread.sleep(500)
			continuation.resume("Done!")
		}
	}
	println(result)
}
