package section3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
	launch(Dispatchers.Default) {
		launch {
			println("${Thread.currentThread().name} launch")
		}

		launch {
			println("${Thread.currentThread().name} launch")
		}

		launch {
			println("${Thread.currentThread().name} launch")
		}
	}
}
