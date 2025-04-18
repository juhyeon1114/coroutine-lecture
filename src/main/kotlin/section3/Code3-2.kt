package section3

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking

val multiThreadDispatcher: CoroutineDispatcher = newFixedThreadPoolContext(2, "Multi")

fun main() = runBlocking<Unit> {
	launch(multiThreadDispatcher) {
		println("${Thread.currentThread().name} launch")
	}

	launch(multiThreadDispatcher) {
		println("${Thread.currentThread().name} launch")
	}
}
