package section1

class ExampleThread : Thread() {

	override fun run() {
		println("${currentThread().name} 시작")
		sleep(3000)
		println("${currentThread().name} 종료")
	}

}

fun main() {
	println("${Thread.currentThread().name} 시작")
	ExampleThread().start()
	Thread.sleep(1000)
	println("${Thread.currentThread().name} 종료")
}
