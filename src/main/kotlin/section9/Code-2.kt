package section9

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

/**
 * 일시 중단 함수에서 코루틴 실행하기
 * - coroutineScope 함수를 사용하면 일시 중단 함수 내부에 새로운 CoroutineScope 객체를 생성할 수 있다.
 * - coroutineScope 함수는 구조화를 깨지 않는 CoroutineScope 객체를 생성한다.
 */
fun main() = runBlocking<Unit> {
	val result = searchByKeyword("Kotlin")
	println("Result: ${result.joinToString()}")
}

suspend fun searchByKeyword(keyword: String) = supervisorScope {
	val deferred1 = async {
		throw Exception("Network Error")
		searchFromDB("Kotlin")
	}
	val deferred2 = async {
		searchFromServer("Kotlin")
	}

	val deferred1Result = try {
		deferred1.await()
	} catch (ex: Exception) {
		arrayOf()
	}

	val deferred2Result = try {
		deferred2.await()
	} catch (ex: Exception) {
		arrayOf()
	}

	return@supervisorScope arrayOf(*deferred1Result, *deferred2Result)
}

suspend fun searchFromDB(keyword: String): Array<String> {
	delay(100L)
	return arrayOf("[DB]${keyword}1", "[DB]${keyword}2")
}

suspend fun searchFromServer(keyword: String): Array<String> {
	delay(100L)
	return arrayOf("[SERVER]${keyword}1", "[SERVER]${keyword}2")
}
