package io.pivotal.noscan.cf.nozzle;

import io.pivotal.cf.nozzle.NozzleProperties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("userpassword")
public class NozzlePropsTest {

	@Autowired
	private NozzleProperties nozzleProperties;

	@Test
	public void testThatPropsArePopulated() {
		assertThat(nozzleProperties).isNotNull();
		assertThat(nozzleProperties.getUser()).isEqualTo("auser");
		assertThat(nozzleProperties.getPassword()).isEqualTo("apass");
		assertThat(nozzleProperties.isSkipSslValidation()).isFalse();
	}

	@Configuration
	@EnableAutoConfiguration
	static class SpringConfig {

		@Bean
		public NozzleProperties nozzleProperties() {
			return new NozzleProperties();
		}

	}

}