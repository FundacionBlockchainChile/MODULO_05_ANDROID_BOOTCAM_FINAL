plugins {
    kotlin("jvm") version "1.9.0"
    application
}

dependencies {
    implementation(kotlin("stdlib"))
}

application {
    mainClass.set("com.example.modulo_05.MainKt")
}
