package org.yang.myref.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

@SpringBootApplication
public class MyRef {
	@Autowired
	DatabaseBibliographyRepository repository;

	@Bean
	public Converter<String, Bibliography> bibliographyConverter() {
		return new Converter<String, Bibliography>() {
			@Override
			public Bibliography convert(String id) {
				return repository.findOne(Long.valueOf(id));
			}
		};
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(YouRef.class, args);
	}
}
