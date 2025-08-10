package section5

import kotlinx.coroutines.*

/**
 * withContext 함수
 * - 인자로 받은 CoroutineDispatcher를 사용해 코루틴의 실행 스레드를 전환하고, 람다식의 코드를 실행한 후 결과값을 반환하는 함수. → 람다식을 실행한 후에는 스레드가 다시 이전의 Dispatcher을 사용하도록 전환됨.
 *
 * withContext와 async-await의 차이
 * - async-await이 연속으로 호출되는 동작을 withContext로 "기능적으로는" 대체 가능
 * - *주의* async-await과 달리 withContext를 사용하는 것은 새로운 '코루틴'을 만드는 것이 아니라 현재의 코루틴이 실행되는 스레드를 전환하는 것이다.
 */
fun main() = runBlocking<Unit> {
    val result = withContext(Dispatchers.IO) {
        delay(1000)
        println("[${Thread.currentThread().name}] 결과값 반환")
        "Result"
    }
    println("[${Thread.currentThread().name}] $result")

    // Don't → 병렬처리되지 않음
    val result1 = withContext(Dispatchers.IO) {
        delay(1000)
        "결과1"
    }
    val result2 = withContext(Dispatchers.IO) {
        delay(1000)
        "결과2"
    }
    val results1 = listOf(result1, result2)
    println(results1)

    // Do
    val result3 = async(Dispatchers.IO) {
        delay(1000)
        return@async "결과3"
    }
    val result4 = async(Dispatchers.IO) {
        delay(1000)
        return@async "결과4"
    }
    val results2 = awaitAll(result3, result4)
    println(results2)

}
