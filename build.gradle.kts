import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.chutneytesting"
version = "0.1.0-SNAPSHOT"
description = "A GitHub project template to help you start with Chutney Kotlin DSL"

val chutneyVersion = "2.9.0"

plugins {
    kotlin("jvm") version "1.9.25" apply true
}

repositories {
    mavenCentral()
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib:1.9.25")

    implementation(enforcedPlatform("com.chutneytesting:chutney-parent:$chutneyVersion"))
    api("com.chutneytesting:chutney-kotlin-dsl:$chutneyVersion")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

task("chutneyReportSite", JavaExec::class) {
    classpath(configurations.runtimeClasspath)

    mainClass.set("com.chutneytesting.kotlin.execution.report.SiteGeneratorMain")
    args("build/reports/chutney")
}

tasks {
    test {
        systemProperty("chutney.environment.rootPath", "chutney/conf/environment") // Default is .chutney/environments
        systemProperty("chutney.report.rootPath", "build/reports/chutney")
        systemProperty("chutney.engine.stepAsTest", false)
        systemProperty("chutney.log.color.enabled", true)

        finalizedBy("chutneyReportSite")
    }
}
