pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }    }
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Add these two lines
        maven { url = uri("https://www.jitpack.io") }
        maven { url = uri("https://maven.zego.im") }
    }
}

rootProject.name = "ChitChat"
include(":app")
    