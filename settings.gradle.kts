rootProject.name = "MangaDT"

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://jitpack.io")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":androidApp")
include(":desktopApp")
include(":shared")
include(":webApp")

// CI may provide this pinned source checkout to avoid relying on a JitPack artifact.
if (file(".ci/kotatsu-parsers-redo").isDirectory) {
    includeBuild(".ci/kotatsu-parsers-redo") {
        dependencySubstitution {
            substitute(module("com.github.Kotatsu-Redo:kotatsu-parsers-redo"))
                .using(project(":"))
        }
    }
}