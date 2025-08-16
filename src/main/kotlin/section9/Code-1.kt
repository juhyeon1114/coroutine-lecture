package section9

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 일시 중단 함수
 * - 일시 중단 함수란 suspend fun 키워드로 선언된 함수로, 함수 내에 '일시 중단 지점'을 포함할 수 있는 함수이다.
 * - 일시 중단 함수는 코루틴의 일시 중단 지점이 포함된 코드를 재사용할 수 있는 코드의 집합으로 만드는데 사용된다.
 * - 일시 중단 지점이 포함될 수 있기 때문에 코루틴에서만 호출 가능하다.
 * - 일시 중단 함수는 '코루틴이 아니다'.
 */
fun main() = runBlocking<Unit> {
	val startTime = System.currentTimeMillis()

	val job1 = launch {
		delayAndPrintHello()
	}
	val job2 = launch {
		delayAndPrintHello()
	}
	joinAll(job1, job2)

	println(System.currentTimeMillis() - startTime)
}

suspend fun delayAndPrintHello() {
	delay(1000L)
	println("Hello")
}
