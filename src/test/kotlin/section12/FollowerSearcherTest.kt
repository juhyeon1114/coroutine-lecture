package section12

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StubOfficialAccountRepository(
	private val users: List<Follower.OfficialAccount>
) : OfficialAccountRepository {
	override suspend fun searchByName(name: String): Array<Follower.OfficialAccount> {
		delay(1000)
		return users.filter { it.name.contains(name) }.toTypedArray()
	}
}

class StubPersonAccountRepository(
	private val users: List<Follower.PersonAccount>
) : PersonAccountRepository {
	override suspend fun searchByName(name: String): Array<Follower.PersonAccount> {
		delay(1000)
		return users.filter { it.name.contains(name) }.toTypedArray()
	}
}

class FollowerSearcherTest {
	private lateinit var followerSearcher: FollowerSearcher

	companion object {
		private val companyA = Follower.OfficialAccount(id = "0x0000", name = "CompanyA")
		private val companyB = Follower.OfficialAccount(id = "0x0001", name = "CompanyB")
		private val companyC = Follower.OfficialAccount(id = "0x0002", name = "CompanyC")

		private val stubOfficialAccountRepository = StubOfficialAccountRepository(
			users = listOf(companyA, companyB, companyC)
		)

		private val personA = Follower.PersonAccount(id = "0x1000", name = "PersonA")
		private val personB = Follower.PersonAccount(id = "0x1001", name = "PersonB")
		private val personC = Follower.PersonAccount(id = "0x1002", name = "PersonC")

		private val stubPersonAccountRepository = StubPersonAccountRepository(
			users = listOf(personA, personB, personC)
		)
	}

	@BeforeEach
	fun beforeEach() {
		followerSearcher = FollowerSearcher(
			stubOfficialAccountRepository,
			stubPersonAccountRepository
		)
	}

	@Test
	@DisplayName("공식 계정과 개인 계정이 합쳐져 반환")
	fun test1() = runTest {
		val search = "A"
		val expected = listOf(companyA, personA)

		val results = followerSearcher.searchByName(search)

		assertEquals(expected, results)
	}

	@Test
	@DisplayName("빈 배열이 반환")
	fun test2() = runTest {
		val search = "Empty"
		val expected = emptyList<Follower>()

		val results = followerSearcher.searchByName(search)

		assertEquals(expected, results)
	}

}
