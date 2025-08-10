package section7

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.System.currentTimeMillis

fun main() = runBlocking<Unit> {
	val startTime = currentTimeMillis()

	launch {
		launch {
			delay(1000)
			println("${Thread.currentThread().name} Completed in ${currentTimeMillis() - startTime} ms - 자식 코루틴 실행 완료")
		}
		println("${Thread.currentThread().name} Completed in ${currentTimeMillis() - startTime} ms - 부모 코루틴의 마지막 코드")
	}.invokeOnCompletion { // 부모 코루틴이 완료될 시 호출되는 콜백 → 부모 코루틴은 자식 코루틴들이 모두 완료되어야 완료된다.
		println("${Thread.currentThread().name} Completed in ${currentTimeMillis() - startTime} ms - 부모 코루틴 실행 완료")
	}

}
