package io.spring.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class SplitConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step myStep1() {
		return stepBuilderFactory.get("myStep1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("myStep1 was executed");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}

	@Bean
	public Step myStep2() {
		return stepBuilderFactory.get("myStep2")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("myStep2 was executed");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}

	@Bean
	public Job splitJob(@Qualifier("foo") Flow foo, @Qualifier("foo") Flow bar) {
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("split");

		Flow flow = flowBuilder.split(new SimpleAsyncTaskExecutor())
				.add(foo, bar)
				.end();

		return jobBuilderFactory.get("splitJob")
				.start(myStep1())
				.next(myStep2())
				.on("COMPLETED").to(flow)
				.end()
				.build();
	}
}
