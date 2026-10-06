package com.arnoldhenderson.cpu;

import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oshi.SystemInfo;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.CentralProcessor;

/**
 * Utility class to retrieve CPU information.
 */

@ThreadSafe
public class GetCpuInfo {
    static final CentralProcessor cpu = new SystemInfo().getHardware().getProcessor();
    private static final Logger logger = LoggerFactory.getLogger(GetCpuInfo.class);

    public static String processorIdentity() {
        String cpuName = cpu.getProcessorIdentifier().getName();
        String cpuModel = cpu.getProcessorIdentifier().getModel();
        logger.info("Returned string CPU Identity");
        return cpuName + " (" + cpuModel + ")";
    }

    public static String vendor() {
        String vendor = cpu.getProcessorIdentifier().getVendor();
        logger.info("Returned string CPU Vendor");
        return vendor;
    }

    public static String vendorArchitecture() {
        String vendorArch = cpu.getProcessorIdentifier().getMicroarchitecture();
        logger.info("Returned string CPU Vendor Architecture");
        return vendorArch;
    }

    public static String architecture() {
        String arch = System.getProperty("os.arch");
        logger.info("Returned string CPU Architecture");
        return arch;
    }

    public static int physicalCores() {
        int physicalCores = cpu.getPhysicalProcessorCount();
        logger.info("Returned int Physical CPU Cores");
        return physicalCores;
    }

    public static int logicalCores() {
        int logicalCores = cpu.getLogicalProcessorCount();
        logger.info("Returned int Logical CPU Cores");
        return logicalCores;
    }

    public static String baseFrequency() {
        String baseFreq = String.valueOf(cpu.getProcessorIdentifier().getVendorFreq());
        logger.info("Returned string CPU Base Frequency");
        return baseFreq;
    }

    public static long maxFrequency() {
        long maxFreq = cpu.getMaxFreq();
        logger.info("Returned long CPU Max Frequency");
        return maxFreq;
    }

    public static long currentFrequency() {
        long[] frequencies = cpu.getCurrentFreq();
        long totalFrequency = 0;
        int availableProcessors = 0;
        for (long frequency : frequencies) {
            if (frequency > 0) {
                totalFrequency += frequency;
                availableProcessors++;
            }
        }
        logger.info("Returned long CPU Current Frequency");
        return availableProcessors == 0 ? -1 : totalFrequency / availableProcessors;
    }

    public static double load() {
        double cpuLoad = cpu.getSystemCpuLoad(1000);
        logger.info("Returned double CPU Usage");
        return cpuLoad;
    }

    public static List<Double> perCoreLoad() {
        long[][] oldTicks = cpu.getProcessorCpuLoadTicks();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        double[] loads = cpu.getProcessorCpuLoadBetweenTicks(oldTicks);
        List<Double> perCoreLoad = new ArrayList<>();
        for (double load : loads) {
            perCoreLoad.add(load);
        }
        logger.info("Returned list of per-core CPU loads");
        return perCoreLoad;
    }

    public static String flags() {
        List<String> flags = cpu.getFeatureFlags();
        logger.info("Returned string CPU Flags");
        return String.join(" ", flags);
    }

    public static String summary() {
        return String.format("%s %s %dC / %dT", processorIdentity(), vendorArchitecture(), physicalCores(), logicalCores());
    }

    public static String verboseSummary() {
        List<String> perCoreLoadPercentages = perCoreLoad().stream()
                .map(load -> String.format("%.2f%%", load * 100))
                .toList();
        return String.format("%s %s %s %dC / %dT Base: %s Max: %s Current: %s Per-core load: %s Flags: %s",
                processorIdentity(),
                vendorArchitecture(),
                architecture(),
                physicalCores(),
                logicalCores(),
                baseFrequency(),
                maxFrequency(),
                currentFrequency(),
                perCoreLoadPercentages,
                flags());
    }
}