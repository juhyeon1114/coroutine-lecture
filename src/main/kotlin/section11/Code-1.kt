package section11

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * 공유 상태를 사용하는 코루틴의 문제와 해결책 - 1. Mutex 사용해 동시 접근 제한하기
 * - 공유 변수의 변경 가능 지점을 임계 영역으로 만들어 동시 접근을 제한할 수 있다.
 * - 코루틴에 대한 임계 영역을 만들기 위해서는 Mutex 객체를 사용하면 된다.
 * - 코루틴이 Mutex 객체의 lock 일시 중단 함수를 호출하면 락이 획득되며, 이후 해당 Mutex 객체에 대해 unlock이 호출될 때까지 다른 코루틴이 해당 임계 영역에 진입할 수 없다.
 *
 * 코루틴에서 Mutex 사용이 권장되는 이유
 * - ReentrantLock의 lock 함수는 특정 스레드에서 락을 획득했다면, 다른 스레드에서 lock함수를 호출할 경우 해당 스레드를 락이 해제될 때까지 블로킹 시킨다.
 * - Mutex의 lock 함수는 일시 중단 함수로 만약 특정 코루틴이 락을 획득했다면, 다른 코루틴에서 lock 함수를 호출할 경우 해당 코루틴은 락이 해제될 때까지 일시 중단됨(스레드 블로킹 X)
 */
var count = 0
var mutex = Mutex()

fun main() = runBlocking {
	withContext(Dispatchers.Default) {
		repeat(1000) {
			launch {
				// === 임계 영역 Start ===
//				mutex.lock()
//				count++
//				mutex.unlock()
				// === 임계 영역 End ===

				mutex.withLock {
					count++
				}
			}
		}
	}
	println("count = $count")
}
