/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package tasks

import org.gradle.api.Project
import org.gradle.api.Plugin

/**
 * A simple 'hello world' plugin.
 */
public class Gradle_build_metrics_pluginPlugin implements Plugin<Project> {
    public void apply(Project project) {
        // Register a task
        project.tasks.register("greeting") {
            doLast {
                println("Hello from plugin 'tasks.greeting'")
            }
        }
    }
}