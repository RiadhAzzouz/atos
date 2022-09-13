package com.atos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
@SpringBootApplication
@EnableConfigurationProperties
public class AtosApplicationTests {

	@Test
	void contextLoads() {
	}

}
