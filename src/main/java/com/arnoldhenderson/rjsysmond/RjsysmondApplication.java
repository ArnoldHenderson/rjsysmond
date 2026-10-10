package com.arnoldhenderson.rjsysmond;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arnoldhenderson.cpu.GetCpuInfo;
import com.arnoldhenderson.ram.GetRamInfo;

@SpringBootApplication
public class RjsysmondApplication {

	private static final Logger logger = LoggerFactory.getLogger(RjsysmondApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(RjsysmondApplication.class, args);
		
		logger.info("RjsysmondApplication started successfully");
		logger.info("Using OSHI library version: 7.7.0");
		System.out.println("");

		logger.info("CPU Info: {}", GetCpuInfo.summary());
		System.out.println("");
		System.out.println("");
		logger.info("CPU Verbose Info: {}", GetCpuInfo.verboseSummary());
		
		logger.info("RAM Info: {}", GetRamInfo.getRamInfo());
		logger.info("RAM Verbose Summary: {}", GetRamInfo.getRamVerboseSummary());

		logger.info("Swap Info: {}", GetRamInfo.getSwapInfo());
	}
}