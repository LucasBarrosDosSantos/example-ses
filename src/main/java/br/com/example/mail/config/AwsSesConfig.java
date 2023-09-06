package br.com.example.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class AwsSesConfig {

	@Value("${aws.credentials.access-key}")
	private String accessKey;

	@Value("${aws.credentials.secret-key}")
	private String secretKey;
	

	@Bean
	public AmazonSimpleEmailService getAmazonSimpleEmailService() {
		return AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(getAwsCredentialProvider())
				.withRegion("us-east-2").build();
	}

	private AWSCredentialsProvider getAwsCredentialProvider() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		return new AWSStaticCredentialsProvider(awsCredentials);
	}
}
