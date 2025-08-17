package section12

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RepeatAddUseCaseTest {

	@Test
	@DisplayName("100번 더하면 100")
	fun testRepeatAddUseCase() = runBlocking {
		// given
		val repeatAddUseCase = RepeatAddUseCase()

		// when
		val result = repeatAddUseCase.add(100)

		// then
		assertEquals(100, result)
	}

}
