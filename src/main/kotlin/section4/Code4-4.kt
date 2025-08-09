package section4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val convertImageJob1 = launch(Dispatchers.Default) {
        delay(1000) // 이미지 1 변환 작업
        println("${Thread.currentThread().name} 이미지 1 변환 완료")
    }

    val convertImageJob2 = launch(Dispatchers.Default) {
        delay(1000) // 이미지 2 변환 작업
        println("${Thread.currentThread().name} 이미지 2 변환 완료")
    }

    convertImageJob1.join()
    convertImageJob2.join()

    val uploadImageJob = launch(Dispatchers.IO) {
        println("${Thread.currentThread().name} 이미지 1, 2 업로드")
    }

}
