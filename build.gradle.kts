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
    implementation("com.github.pvdberg1998:pnet:1.5.10")
}

tasks.test {
    useJUnitPlatform()
}