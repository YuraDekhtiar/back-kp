package com.dk.backkp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dk.backkp.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class BackKpApplication {

	public static void main(String[] args) { SpringApplication.run(BackKpApplication.class, args);
	}

}
