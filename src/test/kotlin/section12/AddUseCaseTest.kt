package section12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AddUseCaseTest {

	private lateinit var addUseCase: AddUseCase

	@BeforeEach
	fun init() {
		addUseCase = AddUseCase()
	}

	@Test
	@DisplayName("1,2,3을 더하면 6")
	fun test1() {
		val result = addUseCase.add(1, 2, 3)

		assertEquals(6, result)
	}

}
