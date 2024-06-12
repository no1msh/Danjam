pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Danjam"
include(":app")

include(":core:model")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":core:androidutil")

include(":feature")
include(":feature:onboading")
