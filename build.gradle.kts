plugins {
    id("java")
}

group = "de.jakob"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<Javadoc>("javadoc") {
    destinationDir = file("$rootDir/docs")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}
