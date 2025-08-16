package section11

import kotlinx.coroutines.*

/**
 * 공유 상태를 사용하는 코루틴의 문제와 해결책 - 2. 전용 스레드 사용
 * - 공유 상태 접근 시 하나의 스레드(전용 스레드)만 사용하면 경쟁 상태 문제를 해결할 수 있다.
 */
var cnt = 0
var countChangeDispatcher = newSingleThreadContext("전용 스레드")

fun main() = runBlocking {
	withContext(Dispatchers.Default) {
		repeat(2000) {
			launch(countChangeDispatcher) {
				cnt++
			}
		}
	}
	println("count = $cnt")
}
