package section11

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 무제한 디스패처
 * - 무제한 디스패처란 코루틴을 자신을 실행시킨 스레드에서 즉시 실행되도록 만드는 디스패처이다.
 */
fun main() = runBlocking<Unit>(Dispatchers.IO) {
	println("Running on thread: ${Thread.currentThread().name}")
	launch(Dispatchers.Unconfined) { // 스레드를 즉시 점유해서 실행됨
		println("Running on thread: ${Thread.currentThread().name}")
	}
}
