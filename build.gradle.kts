plugins {
    id("java")
}

group = "org.ynov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.6.0")
    testImplementation("com.github.jhg023:SimpleNet:1.6.6")
}

tasks.test {
    useJUnitPlatform()
}