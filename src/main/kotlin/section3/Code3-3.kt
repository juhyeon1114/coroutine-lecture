package section3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
	launch(Dispatchers.IO) {
		println("${Thread.currentThread().name} launch")
	}

	launch(Dispatchers.IO) {
		println("${Thread.currentThread().name} launch")
	}

	launch(Dispatchers.IO) {
		println("${Thread.currentThread().name} launch")
	}
}
