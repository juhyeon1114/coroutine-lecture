package section5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 */
fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val participantDeferred1 = async(Dispatchers.IO) {
        delay(1000)
        return@async arrayOf("철수", "영수")
    }
    val participants1 = participantDeferred1.await()

    val participantDeferred2 = async(Dispatchers.IO) {
        delay(1000)
        return@async arrayOf("영희")
    }
    val participants2 = participantDeferred2.await()

    val totalParticipants = listOf(*participants1, *participants2)
    println("Participants: $totalParticipants")
    println("Total time: ${System.currentTimeMillis() - startTime} ms")

    // ---------

    val startTime2 = System.currentTimeMillis()

    val participantDeferred3 = async(Dispatchers.IO) {
        delay(1000)
        return@async arrayOf("철수", "영수")
    }

    val participantDeferred4 = async(Dispatchers.IO) {
        delay(1000)
        return@async arrayOf("영희")
    }

    // Job을 모두 실행하고, 응답값을 대기
//    val totalParticipants2 = listOf(*participantDeferred3.await(), *participantDeferred4.await())
    // 또는
    val totalParticipants2 = awaitAll(participantDeferred3, participantDeferred4)
    println("Participants: $totalParticipants2")
    println("Total time: ${System.currentTimeMillis() - startTime2} ms")

}
