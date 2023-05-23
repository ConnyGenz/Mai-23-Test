plugins {
    id("java")
}

group = "org.example"
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

gradle.taskGraph.whenReady {
    val task = this.allTasks.find { it.name.endsWith(".main()") } as? JavaExec // or whatever other method your Main class runs
    task?.let {
        it.setExecutable(it.javaLauncher.get().executablePath.asFile.absolutePath)
    }
}