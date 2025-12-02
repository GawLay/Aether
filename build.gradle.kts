// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ktlint) apply false
}

// Apply ktlint plugin to root project so aggregate tasks are available
apply(plugin = "org.jlleitschuh.gradle.ktlint")

// Apply ktlint plugin to all subprojects and provide a minimal configuration
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    // Minimal, safe defaults
    extensions.configure<org.jlleitschuh.gradle.ktlint.KtlintExtension>("ktlint") {
        android.set(true)
        ignoreFailures.set(false)
        verbose.set(true)
        filter {
            exclude("**/build/**")
        }
    }
}

// Aggregate task used by CI to format first, then check
val ktlintFormatTask = tasks.named("ktlintFormat")
val ktlintCheckTask = tasks.named("ktlintCheck")
ktlintCheckTask.configure {
    mustRunAfter(ktlintFormatTask)
}

tasks.register("ktLintCheckFormat") {
    group = "verification"
    description = "Runs ktlintFormat first, then ktlintCheck across all modules"
    dependsOn(ktlintFormatTask)
    dependsOn(ktlintCheckTask)
}
