package section5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * async와 Deferred
 * - async 코루틴 빌더를 호출하면, 코루틴이 생성되고, Deferred<T> 타입의 객체가 반환된다.
 * - Deferred는 Job과 같이 코루틴을 추상화한 객체이지만, 코루틴으로부터 생성된 결과값을 감싸는 기능을 추가로 가진다.
 * - 결과값의 타입은 제네릭 타입인 T로 표현된다.
 *
 * async와 launch의 차이점
 * - async의 block 파리미터는 T를 반환하고, launch의 block 파라미터는 Unit을 반환한다.
 * - async의 반환값은 Deferred<T>이고, launch의 반환값은 Job이다.
 * - 나머지는 동일
 */
fun main() = runBlocking<Unit> {
    val networkDeferred = async(Dispatchers.IO) {
        delay(1000)
        return@async "Dummy response"
    }
    val result = networkDeferred.await() // 결과값이 반환될 때까지 runBlock을 일시 중단
    println("Result: $result")
}
