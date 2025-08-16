package section8

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

/**
 * async 코루틴 빌더 함수는 코루틴의 결과값을 Deferred 객체에 감싸고, await 호출 시점에 결과값을 노출한다.
 * 만약 코루틴 실행 도중에 예외가 발생해 결과값이 없다면, Deferred에 대한 await 호출 시 예외가 노출된다.
 */
fun main() = runBlocking<Unit> {
	supervisorScope {
		val deferred1 = async(CoroutineName("Coroutine 1")) {
			println("Starting Coroutine 1")
			throw Exception("Coroutine 1 Exception")
		}
		try {
			deferred1.await()
		} catch (ex: Exception) {
			println("Caught Exception: ${ex.message}")
		}
	}
}
