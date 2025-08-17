package section12

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RepeatAddWithDelayUseCaseTest {

	val testCoroutineScheduler = TestCoroutineScheduler()
	val testDispatcher = StandardTestDispatcher(testCoroutineScheduler)
	val testCoroutineScope = CoroutineScope(testDispatcher)

	@Test
	@DisplayName("runTest 내부에서 advanceUntilIdle 사용하기")
	fun test2() = runTest {
		var result = 0

		launch {
			delay(1000L)
			result += 1
		}
		advanceUntilIdle()

		assertEquals(1, result)
	}

	@Test
	@DisplayName("runTest")
	fun runTestTest() = runTest {
		var result = 0

		delay(1000L)
		result += 1
		delay(1000L)
		result += 1

		assertEquals(2, result)
	}

	@Test
	@DisplayName("2")
	fun testRepeatAddUseCase() {
		val testCoroutineScope2 = TestScope()

		var result = 0

		testCoroutineScope2.launch {
			delay(1000L)
			result = 1
			delay(1000L)
			result = 2
		}
		testCoroutineScope2.advanceUntilIdle()

		assertEquals(2, result)
	}

	@Test
	@DisplayName("가상 시간 조절 테스트")
	fun testDelay() = runBlocking {
		testCoroutineScheduler.advanceTimeBy(5000L)
		assertEquals(5000L, testCoroutineScheduler.currentTime)

		testCoroutineScheduler.advanceTimeBy(6000L)
		assertEquals(11000L, testCoroutineScheduler.currentTime)

		testCoroutineScheduler.advanceTimeBy(10000L)
		assertEquals(21000L, testCoroutineScheduler.currentTime)
	}

}
