package section7

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
	val parentJob = launch(Dispatchers.IO) {
		val dbResultsDeferred = listOf("db1", "db2", "db3").map {
			async {
				delay(1000)
				println("Connected to $it")
				return@async "[${it}] Data"
			}
		}

		val dbResults = dbResultsDeferred.awaitAll()
		println(dbResults)
	}

	parentJob.cancel() // 부모 코루틴에 취소 요청 → 자식 코루틴들에게도 취소 요청이 전파됨
}
