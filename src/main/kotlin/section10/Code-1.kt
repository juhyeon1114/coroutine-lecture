package section10

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

/**
 * 루틴과 서브 루틴
 * - 서브 루틴은 한번 실행되면 끝까지 실행된다.
 * - 루틴에 의해 서브루틴이 호출되면, 루틴은 실행하던 스레드는 서브루틴을 실행하는 데에 사용되어 서브루틴의 실행이 완료될 때가지 다른 작업을 할 수 없다.
 */
fun routine() {
	routineA() // routine의 서브 루틴
	routineB() // routine의 서브 루틴
}

fun routineA() {
	routineB() // routineA의 서브 루틴
}

fun routineB() {
	println("Hello World")
}

/**
 * 루틴과 코루틴
 * - 코루틴은 함께 실행되는 루틴끼리 스레드 사용을 양보하며 함께 실행된다.
 */
fun main() = runBlocking {
	launch {
		while(true) {
			println("실행 1")
			yield() // 스레드 사용 권한을 양보
		}
	}

	while(true) {
		println("실행 2")
		yield() // 스레드 사용 권한을 양보
	}
}
