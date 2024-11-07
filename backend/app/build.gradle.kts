plugins {
    application
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.diffplug.spotless") version "6.25.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation(libs.junit)

    testImplementation("io.cucumber:cucumber-java:7.20.1")
    testImplementation("io.cucumber:cucumber-junit:7.20.1")

    implementation(libs.guava)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "psa.cargahoras.App"
}

spotless {
    java {
        googleJavaFormat()
    }
}

tasks.named<Test>("test") {
    useJUnit()
    exclude("**/*CucumberTest*")
}

tasks.register<Test>("cucumberTest") {
    useJUnit()
    include("**/*CucumberTest*")
}

tasks.named("build") {
    dependsOn("spotlessApply")
}

tasks.register("format") {
    dependsOn("spotlessApply")
}
