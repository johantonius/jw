package com.jurnaliswarga.project;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication

public class DemoApplication {

	@Value("${cloudinary.cloudname}")
	private String cloudName;

	@Value("${cloudinary.apikey}")
	private String apiKey;

	@Value("${cloudinary.apisecret}")
	private String apiSecret;
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Cloudinary cloudinary() {
		Cloudinary cloudinary = null;
		Map config = new HashMap();
		config.put("cloudname", cloudName);
		config.put("apikey", apiKey);
		config.put("apisecret", apiSecret);
		cloudinary = new Cloudinary(config);
		return cloudinary;
	}

}
