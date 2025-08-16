package section10

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 코루틴의 스레드 양보
 * - 코루틴은 작업 중간에 스레드의 사용이 필요 없어지면 스레드를 양보한다. 이때 양보된 스레드는 다른 코루틴이 점유해 사용할 수 있다.
 * - 스레드를 양보하는 주체는 코루틴이다. 따라서 스레드를 양보하기 위해서는 코루틴이 직접 스레드 양보를 위한 함수를 호출해야 한다.
 */
fun main() = runBlocking<Unit> {
	val startTime = System.currentTimeMillis()
	repeat(10) { repeatTime ->
		launch {
			delay(100)
			println("Repeat $repeatTime : ${System.currentTimeMillis() - startTime} ms")
		}
	}
}
