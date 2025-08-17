plugins {
	kotlin("jvm") version "2.2.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
	testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.0-M2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.0-M2")
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}
