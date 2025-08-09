package section4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val updateTokenJob = launch(Dispatchers.IO) {
        println("${Thread.currentThread().name} 토큰 업데이트 시작")
        delay(1000)
        println("${Thread.currentThread().name} 토큰 업데이트 완료")
    }

    // networkCallJob 실행 전에 updateJob.join()을 호출해서, updateTokenJob 다음에 networkCallJob이 동기적으로 처리되도록 한다.
    // join()이 호출된 Job이 완료되기 전까지 다음 코드를 실행하지 않는다.
    updateTokenJob.join()

    val networkCallJob = launch(Dispatchers.IO) {
        println("${Thread.currentThread().name} 네트워크 요청")
    }

}
