package com.cgi.excel.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelBatchController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("excelJob")
	Job excelJob;

	@GetMapping("/run-batch-job/{id}")
	public String handle(@PathVariable Long id) throws Exception
	{

		JobParameters jobParameters = new JobParametersBuilder().addString("source", "Spring Batch5"+id)
				.toJobParameters();
		jobLauncher.run(excelJob, jobParameters);

		return "Batch job has been invoked";
	}
}
