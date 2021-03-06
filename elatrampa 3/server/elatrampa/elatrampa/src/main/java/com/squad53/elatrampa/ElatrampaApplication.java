package com.squad53.elatrampa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.squad53.elatrampa.Contact;
import com.squad53.elatrampa.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

@SpringBootApplication
public class ElatrampaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElatrampaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ContactRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
						.mapToObj(i -> {
							Contact c = new Contact();
							c.setName("Contact " + i);
							c.setEmail("Contact" +i+"@gmail.com");
							c.setPhone("(111) 111-1111");
							return c;
						})
						.map(v -> repository.save(v))
						.forEach(System.out::println);
		};
	}
}
