package section11

import kotlinx.coroutines.runBlocking

/**
 * 코루틴에 실행 옵션 설정하기
 * - 코루틴에 실행 옵션을 주기 위해서는 launch나 async 코루틴 빌더의 start 인자로 CoroutineStart 옵션을 전달하면 된다.
 *
 * CoroutineStart.DEFAULT
 * - 루틴 빌더 함수를 호출한 즉시 코루틴이 생성되고, 코루틴의 실행이 CoroutineDispatcher에 요청된다.
 * - 코루틴 빌더 함수를 호출한 코루틴은 계속해서 실행된다.
 *
 * CoroutineStart.ATOMIC
 * - 코루틴이 실행 요청됐지만, CoroutineDispatcher가 사용할 수 있는 스레드가 없어서 스레드로 보내지지 않은 경우 코루틴은 생성 상태에 머문다.
 *
 * CoroutineStart.UNDISPATCHED
 * - CoroutineDispatcher 객체의 작업 대기열을 거치지 않고, 호출자의 스레드에서 즉시 실행됨
 *
 * CoroutineStart.LAZY
 */
fun main() = runBlocking {

}
