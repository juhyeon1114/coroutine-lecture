package section7

import kotlinx.coroutines.*

/**
 * runBlocking 함수란?
 * - runBlocking 함수가 호출되면, 호출부의 스레드를 차단하고 배타적으로 사용하는 코루틴이 만들어진다.
 * - 호출부의 스레드는 runBlocking 함수가 생성한 코루틴이 실행 완료될 때까지 다른 작업에 사용될 수 없다. → runBlocking 하나는 하나의 스레드를 점유한다. ⚠️
 */
fun main() = runBlocking<Unit> {
}
