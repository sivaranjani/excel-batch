package com.cgi.excel.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.streaming.StreamingXlsxItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import com.cgi.excel.batch.ExcelRowJobProcessor;
import com.cgi.excel.entity.Item;
import com.cgi.excel.vo.ItemVo;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:application.properties")
public class BatchConfiguration {
	

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	ExcelItemWriter writer;

	@Bean
	StreamingXlsxItemReader<ItemVo> excelItemReader() {
		StreamingXlsxItemReader<ItemVo> reader = new StreamingXlsxItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("templates/vegetables.xlsx"));
		reader.setRowMapper(excelRowMapper());
		return reader;
	}

	private RowMapper<ItemVo> excelRowMapper() {

		return new ItemCustomRowMapper();
	}

	@Bean
	public ExcelRowJobProcessor processor() {
		return new ExcelRowJobProcessor();
	}

	@Bean
	public Job excelJob() {
		
		Step step = stepBuilderFactory.get("step1").<ItemVo, Item>chunk(10000).reader(excelItemReader())
				.processor(processor()).writer(writer).build();

		Job job = jobBuilderFactory.get("excelJob").incrementer(new RunIdIncrementer()).start(step).build();
		return job;
	}
}
