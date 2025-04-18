package section3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
	launch(Dispatchers.IO) { // 자식 코루틴에 컨텍스트가 설정되지 않으면, 부모의 컨텍스트를 따라 간다.
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
