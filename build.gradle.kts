plugins {
	kotlin("jvm") version PluginVersions.JVM
}

subprojects {
	apply {
		plugin("org.jetbrains.kotlin.jvm")
		version = PluginVersions.JVM
	}

	apply {
		plugin("org.jetbrains.kotlin.kapt")
		version = PluginVersions.KAPT
	}

	dependencies {
		implementation(Dependencies.KOTLIN_REFLECT)
		implementation(Dependencies.KOTLIN_STDLIB)

		testImplementation(Dependencies.SPRING_TEST)
		testImplementation(Dependencies.MOCKITO_KOTLIN)
	}
}

allprojects {
	group = "team.ampersand"
	version = "0.0.1-SNAPSHOT"

	tasks {
		compileKotlin {
			kotlinOptions {
				freeCompilerArgs = listOf("-Xjsr305=strict")
				jvmTarget = "17"
			}
		}

		compileJava {
			sourceCompatibility = JavaVersion.VERSION_17.majorVersion
		}

		test {
			useJUnitPlatform()
		}
	}

	repositories {
		mavenCentral()
	}
}

val ktlint: Configuration by configurations.creating

dependencies {
	ktlint(Dependencies.KTLINT) {
		attributes {
			attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
		}
	}
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
	inputs.files(inputFiles)
	outputs.dir(outputDir)

	description = "Check Kotlin code style."
	classpath = ktlint
	mainClass.set("com.pinterest.ktlint.Main")
	args = listOf("**/*.kt", "**/*.kts")
}

// Formatting all source files
val ktlintFormat by tasks.creating(JavaExec::class) {
	inputs.files(inputFiles)
	outputs.dir(outputDir)

	description = "Fix Kotlin code style deviations."
	classpath = ktlint
	mainClass.set("com.pinterest.ktlint.Main")
	args = listOf("-F", "**/*.kt")
	jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}