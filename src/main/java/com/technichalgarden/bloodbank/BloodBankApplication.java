package com.technichalgarden.bloodbank;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class BloodBankApplication implements InitializingBean {

	private static final String SPRING_PROFILE_PROD = "prod";

	private static final String SPRING_PROFILE_DEV = "dev";

	private static final String SPRING_PROFILE_LOCAL = "local";

	private static final String SPRING_PROFILES_DEFAULT = "spring.profiles.default";

	private static final Logger log = LoggerFactory.getLogger(BloodBankApplication.class);

	private final Environment env;

	public BloodBankApplication(Environment env) {
		this.env = env;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
		if (activeProfiles.contains(SPRING_PROFILE_DEV) && activeProfiles.contains(SPRING_PROFILE_PROD)) {
			log.error("You have misconfigured your application! It Should not run" +
					"with both 'dev' and 'prod' profiles at the same time.");
		}
		log.info("====================================================");
		log.info(env.getProperty("spring.datasource.url"));
		log.info("====================================================");
	}

	/**
	 * Initializes BloodBankApplication.
	 */
	@PostConstruct
	public void initApplication() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BloodBankApplication.class);
		addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}

	private static void logApplicationStartup(Environment env) {
		String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https")
				.orElse("http");
		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (!Objects.isNull(contextPath)) {
			contextPath = "/";
		}
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException uhe) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}
		log.info("\n----------------------------------------------------------\n\t" +
				"Application '{}' is running! Access URLs:\n\t" +
				"Local: \t\t{}://localhost:{}{}\n\t" +
				"External: \t{}://{}:{}{}\n\t" +
				"Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), protocol, serverPort,
				contextPath, protocol, hostAddress, serverPort, contextPath,
				env.getActiveProfiles());
	}

	private static void addDefaultProfile(SpringApplication app) {
		Map<String, Object> defProperties = new HashMap<>();
		defProperties.put(SPRING_PROFILES_DEFAULT, SPRING_PROFILE_LOCAL);
		app.setDefaultProperties(defProperties);
	}

}
