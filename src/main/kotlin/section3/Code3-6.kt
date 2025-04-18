package section3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
	val limitedParallelism = Dispatchers.Default.limitedParallelism(2)
	repeat(10) {
		launch(limitedParallelism) {
			Thread.sleep(1000)
			println("${Thread.currentThread().name} launch")
		}
	}
}
