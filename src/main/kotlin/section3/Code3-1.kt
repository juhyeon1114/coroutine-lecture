package section3

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

val singleThreadDispatcher: CoroutineDispatcher = newSingleThreadContext("Single")

fun main() = runBlocking<Unit> {
	launch(singleThreadDispatcher) {
		println("${Thread.currentThread().name} launch")
	}
}
