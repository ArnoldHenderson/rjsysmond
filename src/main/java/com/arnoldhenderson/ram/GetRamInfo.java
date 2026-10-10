package com.arnoldhenderson.ram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

/**
 * GetRamInfo
 */
public class GetRamInfo {
    public static GlobalMemory getRamInfo() {
        SystemInfo systemInfo = new SystemInfo();
        return systemInfo.getHardware().getMemory();
    }
    private static final Logger logger = LoggerFactory.getLogger(GetRamInfo.class);
    
    public static void logRamInfo() {
        GlobalMemory memory = getRamInfo();
        logger.info("Total RAM: " + memory.getTotal());
        logger.info("Available RAM: " + memory.getAvailable());
    }

    public static String pageSize() {
        GlobalMemory memory = getRamInfo();
        return String.valueOf(memory.getPageSize());
    }

    public static String getRamVerboseSummary() {
        GlobalMemory memory = getRamInfo();
        return "Total RAM: " + memory.getTotal() + ", Available RAM: " + memory.getAvailable() + ", Used RAM: " + (memory.getTotal() - memory.getAvailable()) + ", Page Size: " + memory.getPageSize();
    }

    // Linux specific RAM information
    public static String getSwapInfo() {
        oshi.hardware.VirtualMemory vm = getRamInfo().getVirtualMemory();
        return "Swap Total: " + vm.getSwapTotal() + ", Swap Used: " + vm.getSwapUsed() + ", Swap Pages In: " + vm.getSwapPagesIn() + ", Swap Pages Out: " + vm.getSwapPagesOut();
    }
}