package section8

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

	launch(CoroutineName("Coroutine1")) {
		try {
			throw Exception("예외 발생")
		} catch (ex: Exception) {
			println(ex.message)
		}
	}

	launch(CoroutineName("Coroutine2")) {
		delay(100)
		println("Coroutine2")
	}

}
