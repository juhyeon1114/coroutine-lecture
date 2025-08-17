package section12

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class FollowerSearcher(
	private val officialAccountRepository: OfficialAccountRepository,
	private val personAccountRepository: PersonAccountRepository
) {

	suspend fun searchByName(name: String): List<Follower> = coroutineScope {
		val deferred1 = async {
			officialAccountRepository.searchByName(name)
		}
		val deferred2 = async {
			personAccountRepository.searchByName(name)
		}

		return@coroutineScope listOf(
			*deferred1.await(),
			*deferred2.await()
		)
	}

}

interface OfficialAccountRepository {
	suspend fun searchByName(name: String): Array<Follower.OfficialAccount>
}

interface PersonAccountRepository {
	suspend fun searchByName(name: String): Array<Follower.PersonAccount>
}
