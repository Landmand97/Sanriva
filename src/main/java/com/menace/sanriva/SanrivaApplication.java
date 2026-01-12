package com.menace.sanriva;

import com.menace.sanriva.organisation.tools.CsvReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class SanrivaApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(SanrivaApplication.class, args);

		Resource resource = context.getResource("classpath:templates/sor.csv");
		String filePath = resource.getFile().getAbsolutePath();

		CsvReader csvReader = context.getBean(CsvReader.class);
		//csvReader.read(filePath, StandardCharsets.ISO_8859_1);
	}

}
