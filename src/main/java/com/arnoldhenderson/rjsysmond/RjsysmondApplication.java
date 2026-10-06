package com.arnoldhenderson.rjsysmond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arnoldhenderson.cpu.GetCpuInfo;

@SpringBootApplication
public class RjsysmondApplication {

	private static final Logger logger = LoggerFactory.getLogger(RjsysmondApplication.class);
	public static void main(String[] args) {
		if (args.length > 0) {
			logger.info("Application started with arguments: {}", (Object) args);
			for (String arg : args) {
				if (arg != null && !arg.isEmpty()) { 
					logger.info("Application argument: {}", arg);
				}
			}
		}

		SpringApplication.run(RjsysmondApplication.class, args);
		
		logger.info("RjsysmondApplication started successfully");
		logger.info("Using OSHI library version: 7.7.0");
		System.out.println("");

		logger.info("CPU Info: {}", GetCpuInfo.summary());
		System.out.println("");
		System.out.println("");
		logger.info("CPU Verbose Info: {}", GetCpuInfo.verboseSummary());
	}

}