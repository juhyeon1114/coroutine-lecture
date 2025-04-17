package section2

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit>(CoroutineName("runBlocking")) {

	println("${Thread.currentThread().name} run blocking")

	launch(CoroutineName("launch")) {
		println("${Thread.currentThread().name} launch")
	}

}
