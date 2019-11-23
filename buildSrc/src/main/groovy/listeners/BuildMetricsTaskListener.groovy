package listeners

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState
import utils.CSVWriter
import utils.NativeOSUtils

import java.util.concurrent.TimeUnit

class BuildMetricsTaskListener implements TaskExecutionListener, BuildListener {
    private static final String DEFAULT_FILE_NAME = "build/task_times.csv"
    private static final List<String> DEFAULT_FILE_HEADER = [
            "task",
            "passed",
            "task_time",
            "java_version",
            "jvm_free_memory",
            "jvm_total_memory",
            "jvm_max_memory",
            "os_name",
            "os_version",
            "architecture",
            "process_cpu_load",
            "system_cpu_load",
            "process_max_virtual_mem_size",
            "system_free_physical_mem_size",
            "system_total_physical_mem_size",
            "system_free_swap_space_size",
            "system_total_swap_space_size",
            "system_num_processors"
    ]

    private long startTime
    private CSVWriter csv

    BuildMetricsTaskListener() {
        super()
        this.csv = new CSVWriter(DEFAULT_FILE_NAME, DEFAULT_FILE_HEADER)
        this.csv.createCSVFile()
    }

    @Override
    void beforeExecute(Task task) {
        this.startTime = System.nanoTime()
    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
        if (task.didWork) {
            this.writeCSVRecord(task)
        }
    }

    /**
     * Get the time that's elapsed since the build started.
     */
    private long getElapsedTime() {
        return TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS)
    }

    /**
     * Creates a CSV record for each task and the build metrics during them.
     */
    private void writeCSVRecord(Task task) {
        this.csv.writeRecord([
                task.path,
                (!task.state.failure).toString(),
                this.getElapsedTime().toString(),
                NativeOSUtils.javaVersion,
                NativeOSUtils.JVMFreeMemorySize,
                NativeOSUtils.JVMTotalMemorySize,
                NativeOSUtils.JVMMaxMemorySize,
                NativeOSUtils.osName,
                NativeOSUtils.osVersion,
                NativeOSUtils.architecture,
                NativeOSUtils.processCPULoad,
                NativeOSUtils.systemCPULoad,
                NativeOSUtils.processMaxVirtualMemorySize,
                NativeOSUtils.systemFreePhysicalMemorySize,
                NativeOSUtils.systemTotalPhysicalMemorySize,
                NativeOSUtils.systemFreeSwapSpaceSize,
                NativeOSUtils.systemTotalSwapSpaceSize,
                NativeOSUtils.systemNumProcessors
        ])
    }

    @Override
    void buildFinished(BuildResult result) {}

    @Override
    void buildStarted(Gradle gradle) {}

    @Override
    void projectsEvaluated(Gradle gradle) {}

    @Override
    void projectsLoaded(Gradle gradle) {}

    @Override
    void settingsEvaluated(Settings settings) {}
}
