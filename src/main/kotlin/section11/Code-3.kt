package section11

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

/**
 * 공유 상태를 사용하는 코루틴의 문제와 해결책 - 3. 원자성 있는 데이터 구조 사용
 * - 원자성 있는 객체는 여러 스레드가 동시에 접근하더라도 안전하게 값을 변경하거나 읽을 수 있도록 하는 객체이다.
 * - * 원자성 있는 객체에서 연산이 실행 중인 경우에는 스레드가 블로킹 될 수 있다.
 */
var atomicCount = AtomicInteger(0)

fun main() = runBlocking {
	withContext(Dispatchers.Default) {
		repeat(3000) {
			launch {
				atomicCount.incrementAndGet()
			}
		}
	}

	println("count = ${atomicCount.get()}")
}
