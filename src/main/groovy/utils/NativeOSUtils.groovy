package utils

import java.lang.management.ManagementFactory
import com.sun.management.OperatingSystemMXBean

class NativeOSUtils {
    private static final OperatingSystemMXBean JAVA_OS_MANAGER = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class)

    static String getJavaVersion() {
        return Runtime.version()
    }

    static String getJVMFreeMemorySize() {
        return Runtime
                .getRuntime()
                .freeMemory()
    }

    static String getJVMTotalMemorySize() {
        return Runtime
                .getRuntime()
                .totalMemory()
    }

    static String getJVMMaxMemorySize() {
        return Runtime
                .getRuntime()
                .maxMemory()
    }

    static String getOsName() {
        return JAVA_OS_MANAGER.name
    }

    static String getOsVersion() {
        return JAVA_OS_MANAGER.version
    }

    static String getArchitecture() {
        return JAVA_OS_MANAGER.arch
    }

    static String getProcessCPULoad() {
        return JAVA_OS_MANAGER.processCpuLoad
    }

    static String getSystemCPULoad() {
        return JAVA_OS_MANAGER.systemCpuLoad
    }

    static String getProcessMaxVirtualMemorySize() {
        return JAVA_OS_MANAGER.committedVirtualMemorySize
    }

    static String getSystemFreePhysicalMemorySize() {
        return JAVA_OS_MANAGER.freePhysicalMemorySize
    }

    static String getSystemTotalPhysicalMemorySize() {
        return JAVA_OS_MANAGER.totalPhysicalMemorySize
    }

    static String getSystemFreeSwapSpaceSize() {
        return JAVA_OS_MANAGER.freeSwapSpaceSize
    }

    static String getSystemTotalSwapSpaceSize() {
        return JAVA_OS_MANAGER.totalSwapSpaceSize
    }

    static String getSystemNumProcessors() {
        return JAVA_OS_MANAGER.availableProcessors
    }
}
