package io.spring.batch.controller;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLaunchingController {

//	@Autowired
//	private JobLauncher jobLauncher;

	@Autowired
	private JobOperator jobOperator;

//	@Autowired
//	private Job job;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void launch(@RequestParam("name") String name) throws Exception {
//		JobParameters jobParameters =
//				new JobParametersBuilder()
//						.addString("name", name)
//						.toJobParameters();
//		this.jobLauncher.run(job, jobParameters);
		this.jobOperator.start("job", String.format("name=%s", name));
	}
}
