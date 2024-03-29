/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package tasks

import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import spock.lang.Specification

/**
 * A simple unit test for the 'tasks.greeting' plugin.
 */
public class Gradle_build_metrics_pluginPluginTest extends Specification {
    def "plugin registers task"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.plugins.apply("tasks.greeting")

        then:
        project.tasks.findByName("greeting") != null
    }
}
