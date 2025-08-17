package section12

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StringStateHolderTest {

	/**
	 * 일반 함수 내부에서 새로운 코루틴을 실행하는 객체에 대한 테스트 문제 해결
	 */
	@Test
	@DisplayName("ABC로 문자열이 변경")
	fun test1() {
		val testDispatcher = StandardTestDispatcher()
		val stateHolder = StringStateHolder(testDispatcher)

		stateHolder.updateStringWithDelay("ABC")

		testDispatcher.scheduler.advanceUntilIdle()
		assertEquals("ABC", stateHolder.stringState)
	}

	/**
	 * 무한 실행되는 코루틴을 테스트할 때의 문제
	 * - backgroundScope은 runTest의 모든 코드가 실행되면 자동으로 취소되고, 이를 통해서 테스트가 무한히 실행되는 것을 방지할 수 있다.
	 */
	@Test
	@DisplayName("backgroundScope")
	fun test2() = runTest {
		var result = 0
		backgroundScope.launch {
			while (true) {
				delay(1000L)
				result += 1
			}
		}

		advanceTimeBy(1500L)
		assertEquals(1, result)
		advanceTimeBy(1000L)
		assertEquals(2, result)
	}

}
